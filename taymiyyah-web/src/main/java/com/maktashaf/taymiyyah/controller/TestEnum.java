package com.maktashaf.taymiyyah.controller;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2/1/12
 * Time: 7:40 PM
 * To change this template use File | Settings | File Templates.
 */
public enum TestEnum {

  Daily {
      @Override
      public Date toBeginningDate() {
        return new Date();
      }

      @Override
      public String getLable(){
        return name();
      }
    },
    Weekly {
      @Override
      public Date toBeginningDate() {
        return new Date();
      }

      @Override
      public String getLable(){
        return name();
      }
    },
    Monthly {
      @Override
      public Date toBeginningDate() {
        return new Date();
      }

      @Override
      public String getLable(){
        return name();
      }
    },
    Yearly {
      @Override
      public Date toBeginningDate() {
        return new Date();
      }

      @Override
      public String getLable(){
        return name();
      }
    };

    public abstract Date toBeginningDate();
    public abstract String getLable();

}
