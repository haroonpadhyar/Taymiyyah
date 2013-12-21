package com.maktashaf.taymiyyah.common.vo;

import com.maktashaf.taymiyyah.common.LocaleEnum;
import com.maktashaf.taymiyyah.common.Translator;

/**
 * @author: Haroon
 */
public class SearchParam {
  private String contextPath;
  private String term;
  private LocaleEnum localeEnum;
  private Translator translator;
  private boolean original;
  private int pageNo;
  private int pageSize;

  private SearchParam(){

  }

  public String getContextPath() {
    return contextPath;
  }

  public String getTerm() {
    return term;
  }

  public LocaleEnum getLocaleEnum() {
    return localeEnum;
  }

  public boolean isOriginal() {
    return original;
  }

  public Translator getTranslator(){
    return translator;
  }

  public int getPageNo(){
    return pageNo;
  }

  public int getPageSize(){
    return pageSize;
  }

  public static SearchParamBuilder builder(){
    return new SearchParamBuilder();
  }

  public static class SearchParamBuilder{
    private SearchParam searchParam;

    private SearchParamBuilder(){
      searchParam = new SearchParam();
    }

    public SearchParamBuilder withContextPath(String contextPath){
      searchParam.contextPath = contextPath;
      return this;
    }

    public SearchParamBuilder withTerm(String term){
      searchParam.term = term;
      return this;
    }

    public SearchParamBuilder withLocale(LocaleEnum localeEnum){
      searchParam.localeEnum = localeEnum;
      return this;
    }

    public SearchParamBuilder withTranslator(Translator translator){
      searchParam.translator = translator;
      return this;
    }

    public SearchParamBuilder withOriginal(boolean original){
      searchParam.original = original;
      return this;
    }

    public SearchParamBuilder withPageNo(int pageNo){
      searchParam.pageNo = pageNo;
      return this;
    }

    public SearchParamBuilder withPageSize(int pageSize){
      searchParam.pageSize = pageSize;
      return this;
    }

    public SearchParam build(){
      return searchParam;
    }
  }
}
