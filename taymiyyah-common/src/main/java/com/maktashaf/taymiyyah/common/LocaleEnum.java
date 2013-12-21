package com.maktashaf.taymiyyah.common;

import java.util.Locale;

import com.google.common.collect.ImmutableBiMap;

/**
 * @author: Haroon
 */
public enum LocaleEnum {
  Original(new Locale("QURA'AN")),
  Ar(new Locale("ar")),
  Ur(new Locale("ur")),
  En(new Locale("en"))
  ;

  private Locale value;
  LocaleEnum(Locale value){
    this.value = value;
  }

  public Locale value(){
    return value;
  }

  public static class localeBiMap {
    private static final ImmutableBiMap<Locale, LocaleEnum> mirror;

    static {
      ImmutableBiMap.Builder<Locale , LocaleEnum> mapBuilder = ImmutableBiMap.builder();
      for (LocaleEnum locale : LocaleEnum.values()) {
        mapBuilder.put(locale.value, locale);
      }
      mirror = mapBuilder.build();
    }

    public static LocaleEnum look(Locale locale) {
      return mirror.get(locale);
    }

    public static Locale look(LocaleEnum localeEnum) {
      return mirror.inverse().get(localeEnum);
    }
  }

  public static class languageBiMap {
    private static final ImmutableBiMap<String, LocaleEnum> mirror;

    static {
      ImmutableBiMap.Builder<String , LocaleEnum> mapBuilder = ImmutableBiMap.builder();
      for (LocaleEnum localeEnum : LocaleEnum.values()) {
        mapBuilder.put(localeEnum.value.getLanguage(), localeEnum);
      }
      mirror = mapBuilder.build();
    }

    public static LocaleEnum look(String language) {
      return mirror.get(language);
    }

    public static String look(LocaleEnum localeEnum) {
      return mirror.inverse().get(localeEnum);
    }
  }

}
