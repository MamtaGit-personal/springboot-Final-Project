package com.mamta.food.controller.support;

public class CreateDishOrderTestSupport extends BaseTest{
  protected String createOrderBody() {
    //@formatter:off
    //used JSON formatter/validator- https://jsonformatter.curiousconcept.com/
   
      return "{\n"
          + "  \"restId\":2,\n"
          + "  \"customerPhone\":\"111-222-1111\",\n"
          + "  \"orderType\":\"PICKUP\",\n"
          + "  \"dishAndQuantity\":{\n"
          + "    \"Taco\":2,\n"
          + "    \"Rice\":3,\n"
          + "    \"Chicken Fajita\":1\n"
          + "  }\n"
          + "}";
         
    // @formatter:on
  }

}
