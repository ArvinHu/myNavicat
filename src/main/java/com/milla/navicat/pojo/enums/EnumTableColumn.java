package com.milla.navicat.pojo.enums;


/**
 * @Package: com.milla.navicat.pojo.enums
 * @Description: <表字段枚举类>
 * @Author: MILLA
 * @CreateDate: 2019/8/28 17:48
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/28 17:48
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public enum EnumTableColumn {

    /**
     * 小整数值1字节	(-128，127)	(0，255)
     */
    TINYINT(255),

    /**
     * 大整数值2字节	(-32768，32767)	(0，65535)
     */
    SMALLINT(255),

    /**
     * 大整数值3字节	(-8388608，8388607)	(0，16777215)
     */
    MEDIUMINT(255),

    /**
     * 大整数值4字节	(-2147483648，2147483647)	(0，4294967295)
     */
    INTEGER(255),

    /**
     * 极大整数值8字节	(-9,223,372,036,854,775,808，9223372036854775807)	(0，18446744073709551615)
     */
    BIGINT(255),

    /**
     * 单精度浮点数值4字节	(-3.402823466E+38，-1.175494351E-38)，0，(1.175494351E-38，3.402823466351E+38)	0，(1.175494351E-38，3.402823466E+38)
     */
    FLOAT(255, 30),

    /**
     * 双精度浮点数值8字节	(-1.7976931348623157E+308，-2.2250738585072014E-308)，0，(2.2250738585072014E-308，1.7976931348623157E+308)	0，(2.2250738585072014E-308，1.7976931348623157E+308)
     */
    DOUBLE(255, 30),

    /**
     * 小数值对DECIMAL(M,D)，如果M>D，为M+2否则为D+2	依赖于M和D的值	依赖于M和D的值
     */
    DECIMAL(255, 30),

    /**
     * 日期值3字节	1000-01-01/9999-12-31	YYYY-MM-DD
     */
    DATE,

    /**
     * 时间值或持续时间3字节	'-838:59:59'/'838:59:59'	HH:MM:SS
     */
    TIME,

    /**
     * 年份值1字节	1901/2155	YYYY
     */
    YEAR,

    /**
     * 混合日期和时间值8字节	1000-01-0100:00:00/9999-12-3123:59:59	YYYY-MM-DDHH:MM:SS
     */
    DATETIME,

    /**
     * 混合日期和时间值，时间戳4字节	1970-01-0100:00:00/2038结束时间是第2147483647秒，北京时间2038-1-1911:14:07，格林尼治时间2038年1月19日凌晨03:14:07YYYYMMDDHHMMSS
     */
    TIMESTAMP,

    /**
     * 定长字符串0-255字节
     */
    CHAR(255),

    /**
     * 变长字符串0-65535字节
     */
    VARCHAR(65535),

    /**
     * 个字符的二进制字符串0-255字节	不超过255
     */
    TINYBLOB(255),

    /**
     * 短文本字符串0-255字节
     */
    TINYTEXT(65535),

    /**
     * 二进制形式的长文本数据0-65535字节
     */
    BLOB(65535),

    /**
     * 长文本数据0-65535字节
     */
    TEXT(16777215),

    /**
     * 二进制形式的中等长度文本数据0-16777215字节
     */
    MEDIUMBLOB(16777215),

    /**
     * 中等长度文本数据0-16777215字节
     */
    MEDIUMTEXT(16777215),

    /**
     * 二进制形式的极大文本数据0-4294967295字节
     */
    LONGBLOB(4294967295L),

    /**
     * 极大文本数据0-4294967295字节
     */
    LONGTEXT(4294967295L);

    //显示宽度
    private long length;
    //小数点
    private int scale;

    EnumTableColumn(long length) {
        this.length = length;
    }

    EnumTableColumn(long length, int scale) {
        this.length = length;
        this.scale = scale;
    }

    EnumTableColumn() {
    }

    public long getLength() {
        return length;
    }

    public int getScale() {
        return scale;
    }
}
