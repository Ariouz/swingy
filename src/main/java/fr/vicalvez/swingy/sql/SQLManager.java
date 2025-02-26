package fr.vicalvez.swingy.sql;


import java.sql.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class SQLManager {

    private Connection connection;
    private final SQLHero sqlHero;

    public SQLManager() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:heroes.db");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        this.sqlHero = new SQLHero(this);
        this.sqlHero.createTables();
    }

    public void update(String query)  {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet updateAndGet(String query)  {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();

            return statement.getGeneratedKeys();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Object query(String qry, Function<ResultSet, Object> consumer) {
        try (
             PreparedStatement s = connection.prepareStatement(qry);
             ResultSet rs = s.executeQuery()) {

            return consumer.apply(rs);
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void query(String qry, Consumer<ResultSet> consumer){
        try (
             PreparedStatement s = connection.prepareStatement(qry);
             ResultSet rs = s.executeQuery()){
            consumer.accept(rs);
        }catch (SQLException e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    public SQLHero getSqlHero() {
        return sqlHero;
    }
}
