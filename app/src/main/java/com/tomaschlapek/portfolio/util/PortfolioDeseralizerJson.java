package com.tomaschlapek.portfolio.util;

/**
 * Created by tomaschlapek on 8/7/16.
 */
//public class PortfolioDeseralizerJson implements JsonDeserializer<Portfolio> {
//
//  @Override
//  public Portfolio deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
//    throws JsonParseException {
//    Portfolio explorer = new Portfolio();
//    JsonArray jsonArray = je.getAsJsonArray();
//    Gson gson = new Gson();
//    for (JsonElement element : jsonArray) {
//      JsonObject jsonObject = element.getAsJsonObject();
//      Project nataCenter = gson.fromJson(jsonObject.get("project"), Project.class);
//      explorer.add(nataCenter);
//    }
//    return explorer;
//  }
//}
