package br.com.felipesutter.certification_nlw.seed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class CreateSeed {

    // Esse código serve para executar o sql na hora de subir a aplicação p/ não
    // precisar de uma outra ferramenta somente para executar o sql

    private final JdbcTemplate jdbcTemplate;

    public CreateSeed(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Cria as informações necessárias para acessar o banco e criá-lo
    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/pg_nlw");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123456");

        CreateSeed createSeed = new CreateSeed(dataSource);
        createSeed.run(args);

    }

    // Método que executa um arquivo sql no path especificado.
    public void run(String... args) {
        executeSqlFile("src/main/resources/create.sql");
    }

    // Método criado para ler o sql e executá-lo
    private void executeSqlFile(String filePath) {
        try {
            String sqlScript = new String(Files.readAllBytes(Paths.get(filePath)));
            jdbcTemplate.execute(sqlScript);

            System.out.println("Seed realizado com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao executar os arquivos" + e.getMessage());
        }
    }

}
