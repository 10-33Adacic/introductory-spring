<#import "macros/common.ftl" as com>
<#import "macros/login.ftl" as log>
<@com.page>
${message!}
    <@log.login "/login" false/>
</@com.page>
