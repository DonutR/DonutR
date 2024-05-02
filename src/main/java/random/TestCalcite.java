package random;

import com.google.common.collect.ImmutableList;
import org.apache.calcite.adapter.jdbc.JdbcSchema;
import org.apache.calcite.adapter.jdbc.JdbcTable;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.plan.Contexts;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlWriter;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.*;
import org.apache.calcite.sql.pretty.SqlPrettyWriter;
import org.apache.calcite.tools.Frameworks;
import org.apache.calcite.tools.Planner;
import org.apache.calcite.tools.ValidationException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCalcite {
    static class MockDb {
        MockDb() {
        }

        static final MockDb INSTANCE = new MockDb();
        private final AtomicInteger id = new AtomicInteger(1);

        public String getUrl() {
            return "jdbc:hsqldb:mem:db" + id.getAndIncrement();
        }
    }

    private static String jdbcUrl;
    private static Connection connection;
    private static DataSource mockDataSource;

    public static void setupTests() throws SQLException {
        jdbcUrl = MockDb.INSTANCE.getUrl();
        connection = DriverManager.getConnection(jdbcUrl, "", "");
        try (Statement s = connection.createStatement()) {
            s.execute("create table mytemptable(" +
                    "id integer not null primary key," +
                    "exampleFoo varchar(25)," +
                    "exampleBar varchar(25))");

            s.execute("insert into mytemptable values(1, 'test', '1234')");
            s.execute("insert into mytemptable values(2, 'test2', 'xyz')");
        }
        mockDataSource = JdbcSchema.dataSource(jdbcUrl, "org.hsqldb.jdbcDriver", "", "");
    }

    public static void parseAndValidateSQL() throws SQLException, SqlParseException, ValidationException {
        // Build our connection
        Connection connection = DriverManager.getConnection("jdbc:calcite:");

        // Unwrap our connection using the CalciteConnection
        CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);

        // Define our parser Configuration
        SqlParser.Config parserConfig = SqlParser.config();

        // Get a pointer to our root schema for our Calcite Connection
        SchemaPlus rootSchema = calciteConnection.getRootSchema();


        // Attach our Postgres Jdbc Datasource to our Root Schema
        rootSchema.add("EXAMPLESCHEMA", JdbcSchema.create(rootSchema, "EXAMPLESCHEMA", mockDataSource, null, null));

        Frameworks.ConfigBuilder config = Frameworks.newConfigBuilder()
                .defaultSchema(rootSchema)
                .parserConfig(parserConfig)
                .context(Contexts.of(calciteConnection.config()));

        Planner planner = Frameworks.getPlanner(config.build());

        SqlNode node = planner.parse("SELECT sum(id) /sum(id) FROM EXAMPLESCHEMA.MYTEMPTABLE");

        SqlNode validateNode = planner.validate(node);
        SqlWriter writer = new SqlPrettyWriter();
        validateNode.unparse(writer, 0, 0);

        // Print out our formatted SQL to the console
        System.out.println(ImmutableList.of(writer.toSqlString().getSql()));
    }

    public static void main(String[] args) throws Exception {
        setupTests();
        parseAndValidateSQL();
    }
}
