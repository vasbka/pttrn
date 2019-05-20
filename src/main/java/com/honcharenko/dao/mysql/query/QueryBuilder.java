package com.honcharenko.dao.mysql.query;


public class QueryBuilder {
    private StringBuilder stringBuilder;
    public static final String ALL = "*";

    public QueryBuilder() {
        this.stringBuilder = new StringBuilder();
    }

    public QueryBuilder select(String value) {
        stringBuilder.append("SELECT ").append(value).append(" ");
        return this;
    }

    public QueryBuilder insert(String tableName) {
        stringBuilder.append("INSERT INTO ").append(tableName).append(" ");
        return this;
    }

    public QueryBuilder values(String values) {
        stringBuilder.append("VALUES(").append(values).append(") ");
        return this;
    }

    public QueryBuilder from(String from) {
        stringBuilder.append("FROM ").append(from).append(" ");
        return this;
    }

    public QueryBuilder where(String where) {
        stringBuilder.append("WHERE ").append(where).append(" ");
        return this;
    }

    public QueryBuilder and() {
        stringBuilder.append("AND ");
        return this;
    }

    public QueryBuilder leftJoin(String join) {
        stringBuilder.append("LEFT JOIN ").append(join).append(" ");
        return this;
    }

    public QueryBuilder rightJoin(String join) {
        stringBuilder.append("RIGHT JOIN ").append(join).append(" ");
        return this;
    }

    public QueryBuilder on(String on) {
        stringBuilder.append("ON ").append(on).append(" ");
        return this;
    }

    public QueryBuilder union() {
        stringBuilder.append("UNION ");
        return this;
    }

    public QueryBuilder delete () {
        stringBuilder.append("DELETE ");
        return this;
    }

    public QueryBuilder update(String tableName) {
        stringBuilder.append("UPDATE ").append(tableName).append(" ");
        return this;
    }

    public QueryBuilder updateSetValues(String values) {
        stringBuilder.append("SET ").append(values).append(" ");
        return this;
    }

    public String build() {
        return this.stringBuilder.toString();
    }
}
