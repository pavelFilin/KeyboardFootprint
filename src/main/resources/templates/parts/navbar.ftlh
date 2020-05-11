<#include "security.ftlh">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Keyboard-footprint</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/main">get footpring</a>
            </li>
        </ul>
        <div>
            <#if user??>
                <ul class="navbar-nav">
                    <li><span class="navbar-text">${name}</span></li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#"
                           id="userDropdownMenu" role="button" data-toggle="dropdown" aria-haspopup="true"
                           aria-expanded="false">
                            <img src="${avatar}"
                                 width="32" height="32" class="rounded-circle">
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdownMenu">
                            <a class="dropdown-item" href="#">Dashboard</a>
                            <a class="dropdown-item" href="#">Edit Profile</a>
                            <a class="dropdown-item" style="padding: 0">
                                <form action="/logout" method="post">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                    <input class="dropdown-item" type="submit" value="Sign Out"/>
                                </form>
                            </a>
                        </div>
                    </li>
                </ul>
                <#else >
                <a class="btn btn-primary" href="/login">login</a>
            </#if>
        </div>
    </div>
</nav>