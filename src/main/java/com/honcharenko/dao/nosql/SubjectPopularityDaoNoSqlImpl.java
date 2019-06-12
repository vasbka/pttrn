package com.honcharenko.dao.nosql;

import com.honcharenko.entity.SubjectPopularity;
import com.mongodb.BasicDBObject;
import org.bson.Document;

import java.util.List;
import java.util.Map;

public class SubjectPopularityDaoNoSqlImpl extends BasicDao<SubjectPopularity> {
    @Override
    public String getCollectionName() {
        return "enrollee";
    }

    @Override
    SubjectPopularity prepareDocumentToEntity(Document entites) {
        for (Map.Entry<String, Object> stringObjectEntry : entites.entrySet()) {

        }
        SubjectPopularity subjectPopularity = new SubjectPopularity(entites.get("_id").toString(),
                Long.valueOf(entites.get("cnt").toString()));
        return subjectPopularity;
    }

    @Override
    Document transofmrEntityToDocument(SubjectPopularity subjectPopularity) {
        return null;
    }

    @Override
    BasicDBObject prepareEntityToUpdate(SubjectPopularity subjectPopularity) {
        return null;
    }

    @Override
    public List<SubjectPopularity> getByAggregation(List<Document> aggregations) {
        return super.getByAggregation(aggregations);
    }
}
