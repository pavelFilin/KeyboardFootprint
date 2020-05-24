<#import "parts/common.ftlh" as c>
<#--<#include "parts/security.ftlh">-->

<@c.page>
    <div class="container">
        <div class="row h-100 justify-content-center align-items-center">
            <form class="col-8 mt-5">
                <#if attemptCount gt 0>
                    <div class="alert alert-primary">
                        Количество попыток авторизации<span id="control-attempt-count">${attemptCount}</span>
                    </div>
                <#else>
                    <div class="alert alert-warning">
                        Необходимо авторизоваться <span id="control-attempt-count">${attemptCount}</span>
                    </div>
                </#if>

                <div>
                    score <span id="control-score">0</span>
                </div>
                <p class="text-center">Введите текст:</p>
                <p class="text-center font-weight-bold" id="control-text">попробуй этих мягких французских булок</p>

                <div class="form-group">
                    <input type="text" class="form-control" id="control-text-test" placeholder="Введите текст указанный выше">
                    <input type="button" value="авторизоваться" class="btn btn-success" id="control-submit">
                </div>
            </form>
        </div>
    </div>

</@c.page>