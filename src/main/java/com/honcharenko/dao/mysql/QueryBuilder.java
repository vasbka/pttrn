package com.honcharenko.dao.mysql;

import javax.management.Query;

public class QueryBuilder {
    private StringBuilder stringBuilder;

    public QueryBuilder() {
        this.stringBuilder = new StringBuilder();
    }

    public QueryBuilder select(String value) {
        stringBuilder.append("SELECT ").append(value).append(" ");
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

    public String build() {
        return this.stringBuilder.toString();
    }
}
