package com.example.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UnitCode {

    AED("아랍에미레이트","디르함"),
    AUD("호주","달러"),
    BRD("바레인","디나르"),
    BND("브루나이","달러"),
    CAD("캐나다","달러"),
    CHF("스위스","프랑"),
    CNN("중국","위안화"),
    DKK("덴마크","크로네"),
    EUR("유럽연합","유로"),
    GBP("영국","파운드"),
    HKD("홍콩","달러"),
    IDR("인도네시아","루피아"),
    JPY("일본","엔"),
    KRW("한국","원"),
    KWD("쿠웨이트","디나르"),
    MYR("말레이시아","링기트"),
    NOK("노르웨이","크로네"),
    NZD("뉴질랜드","달러"),
    SAR("사우디","리알"),
    SEK("스웨덴","크로나"),
    SGD("싱가포르","달러"),
    THB("태국","바트"),
    USD("미국","달러")
    ;

    private final String country;
    private final String unit;

}
