import org.mybatis.generator.api.ShellRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class test {

    public static String getGitRemoteUrl() {
        try {
            Process process = Runtime.getRuntime().exec("git config --get remote.origin.url");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                return line;
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("git", "config", "--global", "user.name");
            Process process = processBuilder.start();

            // 读取标准输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String userName = reader.readLine();
            reader.close();

            processBuilder.command("git", "config", "--global", "user.email");
            process = processBuilder.start();

            // 读取标准输出流
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String userEmail = reader.readLine();
            reader.close();

            System.out.println("Git User Name: " + userName);
            System.out.println("Git User Email: " + userEmail);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
