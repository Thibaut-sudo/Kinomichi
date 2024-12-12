package org.example.kinomichi.configSQLITE;

import org.hibernate.dialect.Dialect;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        super();
    }

    public boolean supportsIdentityColumns() {
        return true;
    }

    public boolean hasDataTypeInIdentityColumn() {
        return false;
    }

    public boolean supportsLimit() {
        return true;
    }

    public String getLimitString(String sql, boolean hasOffset) {
        return sql + (hasOffset ? " LIMIT ? OFFSET ?" : " LIMIT ?");
    }

    public boolean supportsCircularCascadeDelete() {
        return false;
    }
}
