let control = document.getElementById("control-text-test");
let submit = document.getElementById("control-submit");
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
        });
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
    KeyboardFootprint.keyCodes = [32, 1072, 1073, 1074, 1075, 1076, 1077, 1078, 1079,
        1080, 1081, 1082, 1083, 1084, 1085, 1086, 1087, 1088, 1089, 1090, 1091,
        1092, 1093, 1094, 1095, 1096, 1097, 1098, 1099, 1100, 1101, 1102, 1103
    ];

    KeyboardFootprint.keysUp = [];
    KeyboardFootprint.keysDown = [];
    KeyboardFootprint.pressedKey = [];


    KeyboardFootprint.target = document.getElementById("control-text-test");

    const eventCode = {"KEY_DOWN": 0, "KEY_UP": 1, "KEY_PRESSED": 2};

    KeyboardFootprint.isTarget = function (target) {
        return KeyboardFootprint.target.id === target;
    };

    KeyboardFootprint.onUpKey = function (e) {
        if (!KeyboardFootprint.isTarget(e.target.id)) {
            return;
        }

        KeyboardFootprint.keysUp.push(KeyboardFootprint.setKey(eventCode.KEY_UP, (new Date).getTime(), e.key));
    };

    KeyboardFootprint.onDownKey = function (e) {
        if (!KeyboardFootprint.isTarget(e.target.id)) {
            return;
        }

        KeyboardFootprint.keysDown.push(KeyboardFootprint.setKey(eventCode.KEY_DOWN, (new Date).getTime(), e.key));
    };

    KeyboardFootprint.setKey = function (eventNumber, date, key) {
        return {
            'eventCode': eventNumber,
            'date': date,
            'key': key
        }
    }

    KeyboardFootprint.getResult = function () {
        return {
            'keysUp': KeyboardFootprint.keysUp,
            'keysDown': KeyboardFootprint.keysDown
        }
    }
}

