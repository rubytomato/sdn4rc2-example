package com.example.sdn4rc2.domain;

import org.neo4j.ogm.annotation.ResultColumn;
import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class CategoryQueryResult {

  /**
   * <p>The @QueryResult doesn't work.</p>
   * 
   * http://stackoverflow.com/questions/31829600/the-queryresult-doesnt-work
   */
  

  //@ResultColumn
  //Category category;

  @ResultColumn("categoryID")
  public Integer categoryID;
  @ResultColumn("categoryName")
  public String categoryName;
  @ResultColumn("description")
  public String description;
  @ResultColumn("picture")
  public String picture;
  @ResultColumn("count")
  public Integer count;

}
