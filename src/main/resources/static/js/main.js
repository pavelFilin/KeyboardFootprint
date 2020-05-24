let control = document.getElementById("control-text-test");
let submit = document.getElementById("control-submit");
let attemptNumber = document.getElementById("control-attempt-count");
let score = document.getElementById("control-score");
let controlText = document.getElementById("control-text").textContent;

if (control != null) {
    let tracker = new KeyboardFootprint();
    control.onkeyup = tracker.onUpKey;
    control.onkeydown = tracker.onDownKey;
    submit.onclick = function () {
        let response = fetch('/send-data', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(tracker.getResult())
        }).then(response => response.json())
            .then(test => {
                attemptNumber.innerText = test.attemptNumber;
                if (attemptNumber > 0 && attemptNumber.parentElement.classList.contains("alert-warning")) {
                    attemptNumber.parentElement.classList.add("alert-primary");
                    attemptNumber.parentElement.classList.remove("alert-warning");
                }
                score.innerText = test.score;
            });

        tracker = new KeyboardFootprint();
        control.value = "";
    };
}


function KeyboardFootprint() {
    KeyboardFootprint.prototype.onUpKey = function () {
        return KeyboardFootprint.onUpKey.apply(this, arguments);
    };

    KeyboardFootprint.prototype.onDownKey = function () {
        return KeyboardFootprint.onDownKey.apply(this, arguments);
    };

    KeyboardFootprint.prototype.getResult = function () {
        return KeyboardFootprint.getResult.apply(this, arguments);
    };

    KeyboardFootprint.prototype.pressedKey = KeyboardFootprint.pressedKey;
    KeyboardFootprint.prototype.upKey = KeyboardFootprint.keysUp;
    KeyboardFootprint.prototype.downKey = KeyboardFootprint.keysDown;


    KeyboardFootprint.maxKeyPress = 300;
    KeyboardFootprint.maxSeekTime = 1500;
    KeyboardFootprint.keyCodes = [32, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76,
        77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 219, 221, 59, 222,
        188, 190, 191, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 96, 97, 98, 99, 100, 101,
        102, 103, 104, 105
    ];
    // ];[32, 1072, 1073, 1074, 1075, 1076, 1077, 1078, 1079,
    //     1080, 1081, 1082, 1083, 1084, 1085, 1086, 1087, 1088, 1089, 1090, 1091,
    //     1092, 1093, 1094, 1095, 1096, 1097, 1098, 1099, 1100, 1101, 1102, 1103
    // ];

    KeyboardFootprint.keysUp = [];
    KeyboardFootprint.keysDown = [];
    KeyboardFootprint.pressedKey = [];


    KeyboardFootprint.target = document.getElementById("control-text-test");

    const eventCode = {"KEY_DOWN": 0, "KEY_UP": 1, "KEY_PRESSED": 2};

    KeyboardFootprint.isTarget = function (target) {
        return KeyboardFootprint.target.id === target;
    };

    KeyboardFootprint.onUpKey = function (e) {
        if (!KeyboardFootprint.isTarget(e.target.id) || !KeyboardFootprint.checkKey(e.keyCode)) {
            return;
        }

        KeyboardFootprint.keysUp.push(KeyboardFootprint.setKey(eventCode.KEY_UP, (new Date).getTime(), e.key));
    };

    KeyboardFootprint.onDownKey = function (e) {
        if (!KeyboardFootprint.isTarget(e.target.id) || !KeyboardFootprint.checkKey(e.keyCode)) {
            return;
        }

        KeyboardFootprint.keysDown.push(KeyboardFootprint.setKey(eventCode.KEY_DOWN, (new Date).getTime(), e.key));
    };

    KeyboardFootprint.checkKey = function (key) {
        return KeyboardFootprint.keyCodes.includes(key);
    };

    KeyboardFootprint.setKey = function (eventNumber, date, key) {
        return {
            'eventCode': eventNumber,
            'date': date,
            'key': key
        }
    };

    KeyboardFootprint.getResult = function () {
        return {
            'keysUp': KeyboardFootprint.keysUp,
            'keysDown': KeyboardFootprint.keysDown
        }
    }
}

