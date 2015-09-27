package com.money.manager.ex.database;

import android.database.DatabaseUtils;
import android.text.TextUtils;

import java.util.ArrayList;

import info.javaperformance.money.Money;

/**
 * A new database query helper. Uses direct statements, not arguments.
 *
 * Created by Alen Siljak on 08/09/2015.
 */
public class WhereStatementGenerator {

    public WhereStatementGenerator() {
        this.statements = new ArrayList<>();
    }

    private ArrayList<String> statements;

    public void addStatement(String statement) {
        this.statements.add(statement);
    }

    public void addStatement(String field, String operator, Integer argument) {
        this.statements.add(getStatement(field, operator, argument));
    }

    public void addStatement(String field, String operator, Money argument) {
        this.statements.add(getStatement(field, operator, argument));
    }

    public void addStatement(String field, String operator, Object argument) {
        this.statements.add(getStatement(field, operator, argument));
    }

    public String getWhere() {
        String where = "";
        for (String statement : this.statements) {
            where = DatabaseUtils.concatenateWhere(where, statement);
        }
        return where;
    }

    public String getStatement(String field, String operator, Integer argument) {
        StringBuilder sb = new StringBuilder();

        sb.append(field);
        sb.append(operator);
        sb.append(argument);

        return sb.toString();
    }

    public String getStatement(String field, String operator, Money argument) {
        StringBuilder sb = new StringBuilder();

        sb.append(field);
        sb.append(operator);
        sb.append(argument);

        return sb.toString();
    }

    public String getStatement(String field, String operator, Object argument) {
        StringBuilder sb = new StringBuilder();

        sb.append(field);
        sb.append(operator);
        DatabaseUtils.appendValueToSql(sb, argument);

        return sb.toString();
    }

    public String concatenateOr(String a, String b) {
        if (TextUtils.isEmpty(a)) {
            return b;
        }
        if (TextUtils.isEmpty(b)) {
            return a;
        }

        return "( (" + a + ") OR (" + b + ") )";
    }
}