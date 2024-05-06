package com.local.demo.test.shejimoshi.strategy;

import com.alibaba.fastjson.JSON;
import com.local.demo.enums.ResponseCodeEnum;
import com.local.demo.global.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 公共策略工厂
 *
 * @author echo
 * @date 2024/01/12
 */
@Service
@Slf4j
public class CommonStrategyFactory {

    /**
     * 策略
     */
    private static final TwoLevelConcurrentHashMap<Class<? extends BaseCommonStrategy<?, ?, ?>>, Object, BaseCommonStrategy<?, ?, ?>> STRATEGY = new TwoLevelConcurrentHashMap<>();


    /**
     * 建立战略工厂
     *
     * @param routes 路线
     */
    @Autowired
    private void buildStrategyFactory(List<BaseCommonStrategy<?, ?, ?>> routes) {
        log.info("构建通用策略start:{}", routes.size());
        routes.forEach(strategy -> {
            Class<? extends BaseCommonStrategy> aClass = strategy.getClass();
            StrategyType annotation = AnnotationUtils.findAnnotation(aClass, StrategyType.class);
            if (null == annotation) {
                log.error("构建策略路由失败，找不到对应的annotation.clazz:{}", aClass);
                throw new GlobalException(ResponseCodeEnum.ANNOTATION_CAN_NOT_FIND);
            }
            STRATEGY.put(annotation.value(), strategy.routeKey(), strategy);
        });
    }


    /**
     * 处理
     *
     * @param aClass     策略类
     * @param routeKey   路由关键字
     * @param parameters 参数
     * @return {@link R}
     */
    public <P, K, R, S extends BaseCommonStrategy<P, K, R>> R handle(Class<? extends BaseCommonStrategy<P, K, R>> aClass, K routeKey, P parameters) {
        String simpleName = aClass.getSimpleName();
        try {
            BaseCommonStrategy<P, K, R> strategy = (BaseCommonStrategy<P, K, R>) STRATEGY.get(aClass, routeKey);
            if (null == strategy) {
                log.error("未找到对应的处理策略，routeKey:{},parameters:{}", routeKey, JSON.toJSON(parameters));
                throw new GlobalException(ResponseCodeEnum.STRATEGY_CAN_NOT_FIND, simpleName + "未找到对应的处理策略");
            }
            return strategy.handle(parameters);
        } catch (Exception e) {
            log.error("strategy handle occurs error:" + e);
            throw new GlobalException(e.getMessage());
        }
    }

    /**
     * 两级map
     *
     * @author echo
     * @date 2024/01/12
     */
    public static class TwoLevelConcurrentHashMap<K, R, P extends BaseCommonStrategy<?, ?, ?>> {
        /**
         * 层级map
         */
        private ConcurrentHashMap<K, ConcurrentHashMap<R, P>> levelMap;

        /**
         * 两级map
         */
        public TwoLevelConcurrentHashMap() {
            this.levelMap = new ConcurrentHashMap<>();
        }

        /**
         * 放
         *
         * @param k k
         * @param r r
         * @param p p
         */
        public void put(K k, R r, P p) {
            if (levelMap.containsKey(k)) {
                ConcurrentHashMap<R, P> rpConcurrentHashMap = levelMap.get(k);
                rpConcurrentHashMap.put(r, p);
                levelMap.put(k, rpConcurrentHashMap);
                return;
            }
            ConcurrentHashMap<R, P> hashMap = new ConcurrentHashMap<>();
            hashMap.put(r, p);
            levelMap.put(k, hashMap);
        }

        /**
         * 取
         *
         * @param aClass   策略类
         * @param routeKey 路由关键字
         * @return {@link P}
         */
        public P get(K aClass, R routeKey) {
            ConcurrentHashMap<R, P> rpConcurrentHashMap = levelMap.get(aClass);
            return rpConcurrentHashMap.get(routeKey);
        }
    }

}
