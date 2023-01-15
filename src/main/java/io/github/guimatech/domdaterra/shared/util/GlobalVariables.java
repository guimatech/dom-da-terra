package io.github.guimatech.domdaterra.shared.util;

import io.github.guimatech.domdaterra.domain.NoticeLog;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalVariables {

    public static List<NoticeLog> NOTICE_LOGS = new ArrayList<>();
}
