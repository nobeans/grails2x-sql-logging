import groovy.sql.Sql
import java.util.logging.Level

class BootStrap {

    def init = { servletContext ->
        Sql.LOG.level = Level.FINE
    }

    def destroy = {
    }
}
