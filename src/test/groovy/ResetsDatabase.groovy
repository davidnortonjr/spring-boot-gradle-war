import groovy.sql.Sql

trait ResetsDatabase {
    private static final String H2_BACKUP_LOCATION = '/tmp/h2backup.sql'

    void setup() {
        if (new File(H2_BACKUP_LOCATION).exists()) {
            print "Previous test was interrupted and did not clean up after itself. "
            loadH2FromBackup()
        }
        else {
            Sql sql = connectToSql()
            sql.execute("SCRIPT TO ?", [H2_BACKUP_LOCATION])
        }
    }

    void cleanup() {
        loadH2FromBackup()
        new File(H2_BACKUP_LOCATION).delete()
    }

    private void loadH2FromBackup() {
        println "Restoring H2 from backup location."
        Sql sql = connectToSql()
        sql.execute("DROP ALL OBJECTS")
        sql.execute("RUNSCRIPT FROM ?", [H2_BACKUP_LOCATION])
    }

    private Sql connectToSql() {
        Sql.newInstance('jdbc:h2:file:/tmp/myApplicationDb;AUTO_SERVER=TRUE', 'sa', '', 'org.h2.Driver')
    }
}