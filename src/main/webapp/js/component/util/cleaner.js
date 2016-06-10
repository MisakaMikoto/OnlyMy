/**
 * Created by Misaka on 2016-06-10.
 */
class Cleaner {
    constructor() {
        this._target = '';
    }

    set target(target) {
        this._target = target;
    }

    get target() {
        return this._target;
    }

    clear() {
        this.target.innerHTML = '';
        this.target.innerText = '';
    }
}