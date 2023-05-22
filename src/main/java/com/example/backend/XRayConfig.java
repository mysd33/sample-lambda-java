package com.example.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.example.fw.common.jdbc.config.XRayJDBCConfig;

/**
 * X-Rayの設定クラス
 *
 */

//TODO: 現状　SQLトレース対応しようとすると、X-Rayでエラーが出るので対応検討中
//java.lang.ClassCastException: class com.amazonaws.xray.entities.SegmentImpl cannot be cast to class com.amazonaws.xray.entities.Subsegment 

// X-Ray機能の追加、JDBCのX-Rayトレース機能の追加
//@Import({ XRayJDBCConfig.class })
@Configuration
public class XRayConfig {

}
