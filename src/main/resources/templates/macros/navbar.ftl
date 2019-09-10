<#include "security.ftl">
<#import "login.ftl" as l>
<#--<#import "/spring.ftl" as spring>-->

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Dropdown
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
            <button class="dropdown-item" type="button">Action</button>
            <button class="dropdown-item" type="button">Another action</button>
            <button class="dropdown-item" type="button">Something else here</button>
        </div>
    </div>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main"><@spring.message "Messages"></@spring.message></a>
            </li>
        <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">User list</a>
            </li>
        </#if>
            <#--<li class="nav-item mr-1">-->
                <#--<a class="btn btn-info" href="/?lang=ua">UK</a>-->
            <#--</li>-->
            <#--<li class="nav-item mr-1">-->
                <#--<a class="btn btn-info" href="/?lang=en">EN</a>-->
            <#--</li>-->
        </ul>

        <div class="navbar-text mr-3">${name}</div>
        <div class="navbar-text mr-3">
        <a href="?lang=ua">
            <img alt="Українська" src="/static/images/ukraine_icon_64.png" title="Ukrainian" width="50" height="30" border="0"/>
        <#--<@spring.message "lang.ua"/>-->
        </a>
        <a href="?lang=en">
            <img alt="Англійська" src="/static/images/united_kingdom_icon_64.png" title="English" width="50" height="30" border="0"/>
        <#--<@spring.message "lang.eng"/>-->
        </a>
        </div>
        <@l.logout />
    </div>
</nav>
