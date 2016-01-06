/* 
https://github.com/litson/jDialog 
 */
;(function (window, document) {


    /* concat from"\src\core.js" */
    var win = window;
    var doc = document;
    var version = '1.0.0';
    var jDialog = function (message, callBack) {
        /**
         *
         */
        return new jDialog.fn.init(message, callBack);
    };
    
    jDialog.fn = jDialog.prototype = {
        constructor: jDialog,
        /**
         * @method init
         * @param message
         * @param callBack
         * @returns {jDialog}
         */
        init: function (message, callBack) {
    
            this.options = {
                title: 'Dialog Title', // title
                modal: true, //æ˜¯å¦å¯ç”¨æ¨¡å¼çª—å£
                content: '', // messages
                autoHide: 0, // è‡ªåŠ¨é”€æ¯
                fixed: true,
                /**
                 *  ç‚¹å‡»modalä¸ä¼šé”€æ¯
                 */
                preventHide: false,
                callBack: null
            };
    
            this.buttons = [];
    
            // åªå­˜æ´»ä¸€ä¸ªdialog
            if (jDialog.currentDialog) {
                jDialog.currentDialog.remove();
            }
    
            jDialog.currentDialog = this;
    
            if (isPlainObject(message)) {
                jDialog.extend(this.options, message);
    
            } else if (/string|number|boolean/gi.test(typeof(message))) {
                this.options.content = message;
                if (isFunction(callBack)) {
                    this.options.callBack = callBack;
                }
            } else {
                return this;
            }
    
    
            return _renderDOM(this);
        }
    };
    
    /**
     * æµ…copy
     * @returns {*|{}}
     */
    jDialog.extend = jDialog.fn.extend = function () {
    
        var target = arguments[0] || {};
        var options = arguments[1] || {};
        var name;
        var copy;
    
        if (arguments.length === 1) {
            target = this;
            options = arguments[0];
        }
    
        for (name in options) {
            copy = options[name];
            if (copy !== undefined) {
                target[name] = copy;
            }
        }
    
        return target;
    };
    
    /**
     *
     * @type {{constructor: Function, init: Function}|jDialog.fn|*}
     */
    jDialog.fn.init.prototype = jDialog.fn;
    
    /**
     *
     * @param jDialog
     * @private
     */
    function _renderDOM(jDialog) {
        var self = jDialog;
        var wrapper = self.getWrapper();
        var options = self.options;
    
        wrapper
            .appendChild(self.getHeader());
        wrapper
            .appendChild(self.getContainer());
        wrapper
            .appendChild(self.getFooter());
    
    
        if (options.title === '') {
            self.hideHeader();
        }
    
        self.title(options.title);
    
        //
        self.content(options.content);
    
        self.addButton('Cancel', 'destory', function () {
            self.remove();
        });
    
        //
        if (options.modal) {
            self.showModal();
        }
    
        if (options.autoHide) {
            self.autoHide(options.autoHide);
        }
    
        //
        if (options.callBack) {
            self.addButton('Okey', 'apply', options.callBack);
        }
    
        wrapper.addEventListener('click', _eventRouter.bind(self), false);
        wrapper.addEventListener('touchstart', _toggleClass, false);
        wrapper.addEventListener('touchend', _toggleClass, false);
    
        doc.body.appendChild(wrapper);
    
        self.verticalInViewPort(options.fixed)
            .addClass('dialog-zoom-in');
        return self;
    }
    
    /**
     *
     * @param tagName
     * @param attrs
     * @returns {HTMLElement}
     * @private
     */
    function _createElement(tagName, attrs) {
        var element = doc.createElement(tagName);
        jDialog.extend(element, attrs);
        return element;
    }
    
    /**
     *
     * @param event
     * @private
     */
    function _eventRouter(event) {
        var target = event.target;
        var actionName = target.getAttribute('data-dialog-action');
        if (!actionName) {
            return;
        }
        jDialog.event.fire(actionName, target);
    }
    
    /**
     *
     * @param event
     * @private
     */
    function _toggleClass(event) {
        var target = event.target;
        var actionName = target.getAttribute('data-dialog-action');
        if (!actionName) {
            return;
        }
        target.classList.toggle('active');
    }
    
    /**
     *
     * @param context
     * @returns {HTMLElement}
     * @private
     */
    function _createModal(context) {
        var self = context;
        var element = _createElement('div');
        element.style.cssText =
            ';background:rgba(0,0,0,0.3);width:100%;'
            + 'position:absolute;left:0;top:0;'
            + 'height:'
            + Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight)
            + 'px;z-index:'
            + (self.currentDOMIndex - 1);
    
        element.onclick = function () {
            if (!self.options.preventHide) {
                jDialog.event.fire('destory');
            }
        };
    
        return doc.body.appendChild(element)
    }

    /* concat from"\src\helper.js" */
    /**
     *
     * @param fn
     * @returns {boolean}
     */
    function isFunction(fn) {
        return Object.prototype.toString.call(fn) === '[object Function]';
    }
    
    /**
     *
     * @param obj
     * @returns {boolean}
     */
    function isPlainObject(obj) {
        if (obj === null || obj === undefined) {
            return false;
        }
        return obj.constructor == {}.constructor;
    }
    
    /**
     *
     * @param url
     * @returns {boolean}
     */
    //function isUrl(url) {
    //    var regexp =
    //        /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
    //    return regexp.test(url);
    //}
    
    jDialog.extend({
        /**
         * é¡¶çº§ç¼“å­˜å¯¹è±¡ï¼Œç›®å‰æ²¡ä»€ä¹ˆç”¨
         */
        expando: "jDialog" + (version + Math.random()).replace(/\D/g, '')
    });

    /* concat from"\src\event.js" */
    jDialog.event = {
        actions: {},
        add: function (actionName, handler) {
    
            if (isFunction(handler)) {
                this.actions[actionName] = handler;
            }
    
            return this;
        },
        remove: function (actionName) {
            if (this.has(actionName)) {
                return delete this.actions[actionName];
            }
            console.warn(actionName + 'ä¸å­˜åœ¨');
            return false;
        },
        has: function (actionName) {
            if (typeof actionName !== 'string' || !this.actions[actionName]) {
                return false;
            }
            return true;
        },
        once: function (actionName) {
            if (this.has(actionName)) {
                this.fire(actionName)
                    .remove(actionName);
            }
            return this;
        },
        fire: function (actionName, context) {
            if (this.has(actionName)) {
                this.actions[actionName].call(context || win);
            }
            return this;
        }
    };

    /* concat from"\src\operations.js" */
    jDialog.fn.extend({
    
        /**
         * ä¿è¯ position:fixed çš„dialogæ°¸è¿œå¤„äºŽè§†å£å†…ï¼›
         * @param useFixed
         * @returns {*}
         */
        verticalInViewPort: function (useFixed) {
            var docElement = doc.documentElement;
            var clientHeight = docElement.clientHeight;
            var dialogHeight = this.height();
    
            if (useFixed) {
    
                if (dialogHeight > clientHeight) {
                    dialogHeight = 0.75 * clientHeight;
                    this.getContainer().style.height =
                        dialogHeight - (this.height(this.getHeader()) + this.height(this.getFooter())) + 'px';
                }
                this.height(dialogHeight)
                    .toggleLockBody(true)
                    .extend(this.getWrapper().style, {
                        position: 'fixed',
                        marginTop: (-dialogHeight / 2) + "px",
                        top: "50%"
                    });
    
            } else {
    
                // çŸ«æƒ…ï¼Œæ˜ŽçŸ¥é“webkitå–scrollTopæ˜¯ä»Žbodyå–ï¼Œè¿˜è¦è¿™ä¹ˆåš
                var scrollTop = Math.max(doc.body.scrollTop, docElement.scrollTop);
                var top = Math.max((clientHeight - dialogHeight) * 382 / 1000 + scrollTop, scrollTop);
    
                this.top(top)
                    .height('auto')
                    .toggleLockBody(false)
                    .getContainer().style.height = 'auto';
    
            }
    
            return this;
        },
    
        /**
         * é”ä½bodyçš„scrollï¼Œä¸è®©å…¶æ»šåŠ¨ï¼›
         * @param useLock
         */
        toggleLockBody: function (useLock) {
    
            var header = this.getHeader();
            var footer = this.getFooter();
            var modal = this.getModal();
            var ev = 'ontouchmove';
    
            header[ev] = footer[ev] = modal[ev] = useLock ? function () {
                return false;
            } : null;
    
            return this;
        },
    
        /**
         *  èŽ·å–dialogçš„DOMç»“æž„
         * @returns {HTMLElement|*|wrapper}
         */
        getWrapper: function () {
            if (!this.wrapper) {
    
                this.wrapper = _createElement('div', {
                    className: 'dialog'
                });
    
                this.wrapper.style.zIndex = this.currentDOMIndex = 614;
            }
    
            return this.wrapper;
        },
    
        /**
         *  èŽ·å–é¡µå¤´çš„DOMç»“æž„
         * @returns {HTMLElement|*|header}
         */
        getHeader: function () {
            return this.header ? this.header : this.header = _createElement('div', {
                className: 'dialog-header'
            });
        },
    
        /**
         * éšè—é¡µå¤´
         * @returns {*}
         */
        hideHeader: function () {
            var header = this.getHeader();
            var height = this.height(header);
            this.height(this.height() - height);
            header.style.display = 'none';
            return this;
        },
    
        /**
         * èŽ·å–å½“å‰dialogå†…å®¹çš„DOMç»“æž„
         * @returns {HTMLElement|*|container}
         */
        getContainer: function () {
            return this.container ? this.container : this.container = _createElement('div', {
                className: 'dialog-body'
            });
        },
    
        /**
         * èŽ·å–é¡µå°¾çš„domç»“æž„
         * @returns {HTMLElement|*|footer}
         */
        getFooter: function () {
            return this.footer ? this.footer : this.footer = _createElement('div', {
                className: 'dialog-footer'
            });
        },
    
        /**
         * éšè—é¡µå°¾
         * @returns {*}
         */
        hideFooter: function () {
            var footer = this.getFooter();
            var height = this.height(footer);
            this.height(this.height() - height);
            footer.style.display = 'none';
            return this;
        },
    
        /**
         * æ·»åŠ æŒ‰é’®åŠäº‹ä»¶
         * @method addButton
         * @param text
         * @param actionName
         * @param handler
         * @returns {*}
         */
        addButton: function (text, actionName, handler) {
    
            // æ¨¡æ‹Ÿé‡è½½
            var fnKey = ("jDialog" + Math.random()).replace(/\D/g, '');
    
            var defaultText = 'Okey';
    
            // å¦‚æžœç¬¬ä¸€ä¸ªå‚æ•°æ˜¯ä¸€ä¸ªfunction
            if (isFunction(text)) {
                return this.addButton(defaultText, actionName || fnKey, text);
            }
    
            if (isFunction(actionName)) {
                return this.addButton(text, fnKey, actionName);
            }
    
    
            var element = _createElement('a', {
                href: 'javascript:;',
                className: 'dialog-btn',
                innerHTML: text || defaultText
            });
    
            if (!actionName) {
                actionName = "destory";
            } else {
                jDialog.event.add(actionName, handler);
            }
    
            element.setAttribute('data-dialog-action', actionName);
    
            var footer = this.getFooter();
            if (this.buttons.length) {
                this.addClass("dialog-btn-primary", element);
                footer.insertBefore(element, footer.childNodes.item(0));
            } else {
                footer.appendChild(element);
            }
            this.buttons.push(element);
    
            return this;
        },
    
        /**
         * å¦‚æžœä¿è¯æ¯ä¸ªæŒ‰é’®å¯¹åº”é˜Ÿé‡Œçš„actionï¼Œåˆ™å¯æ”¾å¿ƒç§»é™¤button
         * @param index
         * @returns {*}
         */
        delButton: function (index) {
            var button = this.getButton(index);
            var actionName;
            if (button) {
                actionName = button.getAttribute('data-dialog-action');
                (actionName != 'destory') && jDialog.event.remove(actionName);
                this.getFooter().removeChild(button);
                var i = this.buttons.indexOf(button);
                this.buttons.splice(i, 1);
            }
            return this;
        },
    
        /**
         *
         * @param index
         * @returns {*}
         */
        getButton: function (index) {
            var buttons = this.buttons.slice().reverse();
            if (buttons[index]) {
                return buttons[index];
            } else {
                return null;
            }
        },
        /**
         * ä¸ºå½“å‰dialogæ·»åŠ class
         * @param className
         * @returns {*}
         */
        addClass: function (className, context) {
    
            context = context || this.getWrapper();
    
            if (context.nodeType === 1 && typeof className === 'string') {
                context.classList.add(className);
            }
    
            return this;
        },
    
        /**
         * ä¸ºå½“å‰dialogæ·»åŠ remove
         * @param className
         */
        removeClass: function (className, context) {
    
            context = context || this.getWrapper();
    
            if (context.nodeType === 1 && typeof className === 'string') {
                context.classList.remove(className);
            }
            return this;
        },
    
        /**
         * è®¾ç½®è‡ªåŠ¨éšè—æ—¶é—´
         * @param delay  ä¸º0ï¼Œç›´æŽ¥é”€æ¯ï¼›ä¸è®¾ç½®ï¼Œé‡‡ç”¨é»˜è®¤ç”¨æˆ·è®¾ç½®ï¼›
         * @returns {*}
         */
        autoHide: function (delay) {
            if (!jDialog.currentDialog) {
                return this;
            }
    
            // 0åˆ™è‡ªåŠ¨é”€æ¯ï¼›
            if (delay == 0) {
                return this.remove();
            }
    
            //
            if (delay === undefined) {
                return this.autoHide(this.options.autoHide);
            }
    
            // å°†ä¼šå·²æœ€æ–°çš„delayä¸ºå‡†
            if (this.autoHideTimer) {
                clearTimeout(this.autoHideTimer);
            }
    
            this.autoHideTimer = setTimeout(function () {
                this.remove();
                clearTimeout(this.autoHideTimer);
                this.autoHideTimer = null;
            }.bind(this), delay * 1000);
    
            return this;
        },
    
        /**
         *
         * @returns {*}
         */
        remove: function () {
    
            this.toggleLockBody(false);
    
            if (this.wrapper) {
                this.wrapper.removeEventListener('click', _eventRouter, false);
                this.wrapper.removeEventListener('touchstart', _toggleClass, false);
                this.wrapper.removeEventListener('touchend', _toggleClass, false);
                doc.body.removeChild(this.wrapper);
            }
    
            if (this.modal) {
                this.modal.onclick = null;
                doc.body.removeChild(this.modal);
            }
    
            if (this.autoHideTimer) {
                clearTimeout(this.autoHideTimer);
            }
    
            jDialog.currentDialog
                = this.buttons
                = this.container
                = this.footer
                = this.header
                = this.options
                = this.wrapper
                = this.modal
                = null;
    
            return this;
        },
    
        /**
         * èŽ·å–å½“å‰dialogçš„Modalçš„DOMç»“æž„
         * @returns {modal|*}
         */
        getModal: function () {
            if (!this.modal) {
                this.modal = _createModal(this);
            }
            return this.modal;
        },
    
        /**
         * éšè—å½“å‰dialogçš„Modal
         * @returns {*}
         */
        hideModal: function () {
            this.getModal().style.display = 'none';
            return this;
        },
    
        /**
         * æ˜¾ç¤ºå½“å‰dialogçš„Modal
         * @returns {*}
         */
        showModal: function () {
            this.getModal().style.display = '';
            return this;
        },
    
        getCloseBtn: function () {
            if (!this.closeBtn) {
                this.closeBtn = _createElement('span', {
                    innerHTML: 'å…³é—­',
                    'data-dialog-action': 'destory',
                    className: 'dialog-btn-dismiss'
                });
            }
            return this.closeBtn;
        }
    });

    /* concat from"\src\setting.js" */
    /**
     *  è®¾ç½®å‡½æ•°é›†
     * @param number
     * @returns {*}
     */
    var addPixelUnit = function (number) {
        if (!/em|px|rem|pt|%|auto/gi.test(number)) {
            number = number + 'px';
        }
        return number;
    };
    
    jDialog.fn.extend({
    
        /**
         * è¿”å›žå½“å‰çš„titleæˆ–ä¸ºdialogè®¾ç½®title
         * @param text
         * @returns {*}
         */
        title: function (value) {
            if (typeof value === 'undefined') {
                return this.options.title;
            }
    
            this.getHeader().innerHTML = this.options.title = value;
            return this;
        },
    
        /**
         * è¿”å›žå½“å‰è®¾ç½®çš„contentæˆ–è®¾ç½®content
         * @param value
         * @returns {*}
         */
        content: function (value) {
            if (typeof value === 'undefined') {
                return this.options.content;
            }
            this.getContainer().innerHTML = this.options.content = value;
            return this;
        },
    
        /**
         * è¿”å›žå½“å‰çš„heightæˆ–ä¸ºdialogè®¾ç½®height
         * @param value
         * @returns {*}
         */
        height: function (value) {
    
            if (typeof value === 'undefined') {
                return this.height(this.getWrapper());
            }
    
            if (value.nodeType === 1) {
                return value.offsetHeight;
            }
    
            this.wrapper.style.height = addPixelUnit(value);
            return this;
        },
    
        /**
         * è¿”å›žå½“å‰dialogçš„å®½åº¦æˆ–ä¸ºdialogè®¾ç½®å®½åº¦
         * @param value
         * @returns {*}
         */
        width: function (value) {
            if (typeof value === 'undefined') {
                return this.width(this.getWrapper());
            }
    
            if (value.nodeType === 1) {
                return value.offsetWidth;
            }
    
            jDialog.extend(this.wrapper.style, {
                width: addPixelUnit(value),
                marginLeft: addPixelUnit(-(parseFloat(value) / 2))
            });
    
            return this;
        },
    
        /**
         * è¿”å›žå½“å‰çš„z-indexå€¼æˆ–ä¸ºdialogè®¾ç½®z-index
         * @param index
         * @returns {*}
         */
        index: function (value) {
            if (typeof value === 'undefined') {
                return this.currentDOMIndex;
            }
            this.currentDOMIndex = value;
            this.wrapper.style.zIndex = this.currentDOMIndex;
            // æ°¸è¿œæ¯”wrapperå°1
            this.getModal().style.zIndex = this.currentDOMIndex - 1;
            return this;
        },
    
        /**
         * è¿”å›žå½“å‰çš„topå€¼æˆ–è€…ä¸ºdialogè®¾ç½®top
         * @param value
         * @returns {*}
         */
        top: function (value) {
    
            if (typeof value === 'undefined') {
                return win.getComputedStyle(this.getWrapper()).top;
            }
    
            jDialog.extend(this.wrapper.style, {
                top: addPixelUnit(value),
                marginTop: ''
            });
    
            return this;
        },
    
        /**
         * ç›¸å¯¹äºŽè§†å£ï¼Œè¿˜æ˜¯ç›¸å¯¹äºŽæ–‡æ¡£æµ
         * @param isUse
         * @returns {*}
         */
        fixed: function (isUse) {
            var flag = true;
            if (!isUse || (typeof isUse !== "undefined")) {
                flag = false;
                this.getWrapper().style.position = 'absolute';
            }
            return this.verticalInViewPort(flag);
        },
    
        /**
         *
         * @returns {preventHide}
         */
        preventHide: function () {
            this.options.preventHide = true;
            return this;
        }
    });

    /* concat from"\src\components.js" */
    /**
     *  å°è£…ä¸€äº›å¸¸ç”¨çš„dialog
     */
    jDialog.extend({
        alert: function (message) {
            return jDialog(message);
        },
        toast: function (message, delay) {
            var dialog = jDialog(message)
                .addClass('dialog-toast');
            var container = dialog.getContainer();
            var height = dialog.height(container);
            dialog.getContainer().style.textAlign = 'center';
            dialog
                .hideFooter()
                .hideHeader()
                .hideModal()
                .height(height)
                .autoHide(delay || 3);
            return dialog;
        },
        error: function (message, callBack) {
            return jDialog(message, callBack).addClass('dialog-error');
        }
    });

    /* concat from"\src\compatibleAMD.js" */
    if (typeof define === "function" && define.amd) {
        define("jdialog", [], function() {
            return jDialog;
        });
    } else {
        win.jDialog = jDialog;
    }

})(window, window.document);

//# sourceMappingURL=jDialog.js.map