package com.iipsen2.app.interfaces.enums;

public class LikeTypeTranslator {
    public static LikeType translate(int type) {
        switch (type) {
            case 1:
                return LikeType.LIKE;
            case 2:
                return LikeType.DISLIKE;
            case 3:
                return LikeType.UP_VOTE;
            case 4:
                return LikeType.DOWN_VOTE;
            default:
                return LikeType.NEUTRAL;
        }
    }
}
