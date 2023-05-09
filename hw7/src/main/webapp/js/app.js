window.notify = function (message, className = "success") {
    $.notify(message, {
        position: "right bottom",
        className: className
    });
}

window.makePost = function (action, dataValues, successFunction) {
    $.ajax({
        type: "POST",
        url: "",
        dataType: "json",
        data: {action: action, ...dataValues},
        success: function (response) {
            if (successFunction != null) {
                successFunction(response)
            }
            if (response["redirect"]) {
                location.href = response["redirect"];
            }
        }
    });
}

window.validateUserForm = function ($form, formClass, action) {
    $(formClass + " form").submit(function () {
        const login = $form.find("input[name='login']").val();
        const password = $form.find("input[name='password']").val();
        const $error = $form.find(".error");
        window.makePost(action,
            {
                login: login,
                password: password
            },
            function (response) {
                if (response["error"]) {
                    $error.text(response["error"]);
                    return true;
                }
            }
        )
        return false;
    });
}