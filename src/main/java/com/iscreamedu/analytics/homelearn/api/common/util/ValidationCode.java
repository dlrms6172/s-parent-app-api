package com.iscreamedu.analytics.homelearn.api.common.util;

/**
 * ValidationCode Enum (Response 코드 정의)
 * @author hy
 * @since 2019.09.05
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      		수정자		          수정내용
 *  ----------  --------    ---------------------------
 *  2019.09.05	hy			초기생성
 *  </pre>
 */
public enum ValidationCode {

    SUCCESS("0000", "정상,성공"),
    REQUIRED("1100", "필수값 누락"),
    LENGTH("1200", "LENGTH 오류"),
    NUMERIC("1310", "숫자형 오류"),
    MONTH_WEEK("1320", "월간/주간구분 파라미터 오류 - M/W"),
    DATE("1330", "DATE형 오류"),
    RANGE("1340", "숫자범위 오류"),
    SUBJNM("1350", "과목명 오류(subjNm) 체크 – 국어, 수학, 사회, 과학, 영어, 통합"),
    NO_DATA("2000", "데이터 없음"),
    SYSTEM_ERROR("9999", "시스템 오류,기타 오류");

    private String code;
    private String message;

    private ValidationCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return this.code;
    }

}

