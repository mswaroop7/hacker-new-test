package com.example.hackernewstest.comments.manager;

import com.example.hackernewstest.comments.models.HNComment;
import java.util.Comparator;

public class HNCommentsComparator implements Comparator<HNComment> {

    @Override
    public int compare(HNComment o1, HNComment o2) {
        int sizeO1 = 0;
        int sizeO2 = 0;
        if (o1.getKids() != null) {
            sizeO1 = o1.getKids().size();
        }

        if (o2.getKids() != null) {
            sizeO2 = o2.getKids().size();
        }

        if (sizeO1 > sizeO2) {
            return -1;
        }
        else
        if (sizeO1 < sizeO2) {
            return 1;
        }
        return 1;
    }
}
