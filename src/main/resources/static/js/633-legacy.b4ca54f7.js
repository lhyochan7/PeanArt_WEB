"use strict";(self["webpackChunkpean_art"]=self["webpackChunkpean_art"]||[]).push([[633],{1633:function(e,t,r){r.r(t),r.d(t,{default:function(){return P}});var i=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("v-app",[i("nav_bar"),i("v-container",{attrs:{height:"100%"}},[i("v-row",[i("v-col",{attrs:{md:"6"}},[i("v-img",{attrs:{src:r(4503),height:"100%"}})],1),i("v-col",{attrs:{md:"6"}},[i("v-form",{ref:"form"},[i("div",{staticClass:"d-flex"},[i("v-text-field",{attrs:{rules:e.usr_emailChecked?[]:[e.rules.required,e.rules.email,e.emailValid],label:"이메일",outlined:"",disabled:e.usr_emailChecked},model:{value:e.usr_email,callback:function(t){e.usr_email=t},expression:"usr_email"}}),i("v-btn",{staticClass:"ml-2",attrs:{"x-large":"",outlined:""},on:{click:function(t){e.usr_emailChecked?e.clearEmail():e.emailCheckRequest()}}},[e._v(e._s(e.usr_emailChecked?"중복확인 취소":"중복확인"))])],1),i("v-alert",{staticClass:"mt-n7",attrs:{type:"error",value:e.emailAlert,transition:"slide-y-transition"}},[e._v("중복된 이메일이 존재합니다. 다시 시도해주세요.")]),i("div",{staticClass:"d-flex"},[i("v-text-field",{attrs:{rules:e.usr_nicknameChecked?[]:[e.rules.required,e.nicknameValid],label:"닉네임",outlined:"",disabled:e.usr_nicknameChecked},model:{value:e.usr_nickname,callback:function(t){e.usr_nickname=t},expression:"usr_nickname"}}),i("v-btn",{staticClass:"ml-2",attrs:{"x-large":"",outlined:""},on:{click:function(t){e.usr_nicknameChecked?e.clearNickname():e.nicknameCheckRequest()}}},[e._v(e._s(e.usr_nicknameChecked?"중복확인 취소":"중복확인"))])],1),i("v-alert",{staticClass:"mt-n7",attrs:{type:"error",value:e.nicknameAlert,transition:"slide-y-transition"}},[e._v("중복된 닉네임이 존재합니다. 다시 시도해주세요.")]),i("v-text-field",{attrs:{"append-icon":e.show_pwd?"mdi-eye":"mdi-eye-off",type:e.show_pwd?"text":"password",rules:[e.rules.required,e.rules.password_rule],label:"비밀번호",outlined:""},on:{"click:append":function(t){e.show_pwd=!e.show_pwd}},model:{value:e.password,callback:function(t){e.password=t},expression:"password"}}),i("v-text-field",{attrs:{"append-icon":e.show_pwd?"mdi-eye":"mdi-eye-off",type:e.show_pwd?"text":"password",rules:[e.rules.required,e.matchPassword,e.rules.password_rule],label:"비밀번호 확인",outlined:""},on:{"click:append":function(t){e.show_pwd=!e.show_pwd}},model:{value:e.passwordCheck,callback:function(t){e.passwordCheck=t},expression:"passwordCheck"}}),i("v-text-field",{attrs:{rules:[e.rules.required,e.rules.name],label:"이름",outlined:""},model:{value:e.usr_name,callback:function(t){e.usr_name=t},expression:"usr_name"}}),i("v-text-field",{attrs:{label:"휴대폰 번호 (선택사항)",rules:[e.rules.phoneNumber],outlined:""},on:{input:e.acceptNumber},model:{value:e.usr_phone,callback:function(t){e.usr_phone=t},expression:"usr_phone"}}),i("v-text-field",{attrs:{label:"주소 (선택사항)",outlined:"",readonly:""},on:{click:e.loadAddress},model:{value:e.usr_addr,callback:function(t){e.usr_addr=t},expression:"usr_addr"}})],1),i("v-btn",{attrs:{outlined:"",rounded:"",block:""},on:{click:e.registerRequest}},[e._v("회원가입")])],1)],1)],1)],1)},n=[],s=(r(4916),r(7601),r(4723),r(5306),r(6053)),a=r(9669),o=r.n(a),l={name:"userRegisterPage",components:{nav_bar:s.Z},data:function(){return{show_pwd:!1,usr_email:"",usr_emailChecked:!1,password:"",passwordCheck:"",usr_name:"",usr_nickname:"",usr_nicknameChecked:!1,usr_addr:"",usr_phone:"",emailAlert:!1,nicknameAlert:!1,rules:{required:function(e){return!!e||"필수."},email:function(e){var t=/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;return e.length<50&&t.test(e)||"올바르지 않은 e-mail 입니다."},password_rule:function(e){var t=/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/;return t.test(e)||"비밀번호는 8자 이상, 영문 대, 소문자 및 특수기호를 조합하여야 합니다."},name:function(e){return e.length<20||"이름은 20자 이내여야 합니다."},nickname:function(e){return e.length<50||"닉네임은 50자 이내여야 합니다."},phoneNumber:function(e){var t=/^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;return 0==e.length||t.test(e)||"올바른 휴대폰 번호를 입력해 주세요."}}}},methods:{matchPassword:function(){return this.password===this.passwordCheck||"비밀번호가 다릅니다."},nicknameValid:function(e){return e.length<50?!0===this.usr_nicknameChecked||"닉네임 중복확인이 필요합니다.":"닉네임은 50자 이내여야 합니다."},emailValid:function(){return!0===this.usr_emailChecked||"이메일 중복확인이 필요합니다."},loadAddress:function(){var e=this;new window.daum.Postcode({onComplete:function(t){e.usr_addr=t.roadAddress}}).open()},acceptNumber:function(){var e=this.usr_phone.replace(/\D/g,"").match(/(\d{0,3})(\d{0,4})(\d{0,4})/);this.usr_phone=e[2]?e[1]+"-"+e[2]+(e[3]?"-"+e[3]:""):e[1]},registerRequest:function(){var e=this;if(this.$refs.form.validate()){var t={usrId:this.usr_email,usrPw:this.password,usrNickname:this.usr_nickname,usrName:this.usr_name,usrAdrs:this.usr_addr,usrPhone:this.usr_phone};console.log(t),o().post("http://localhost:8080/join.do",t,{headers:{"Content-Type":"application/json"}}).then((function(t){console.log(t),200===t.status?(alert("회원가입에 성공했습니다!"),e.$router.push("/login.do")):alert("회원가입에 실패했습니다. 다시 시도해주세요")}))}},emailCheckRequest:function(){var e=this;if(0!==this.usr_email.length){var t={params:{email:this.usr_email}};o().get("http://localhost:8080/duplicheck.do",t).then((function(t){console.log(t),404!==t.status?"email"===t.data.type&&(!1===t.data.duplicated?e.usr_emailChecked=!0:(e.usr_emailChecked=!1,e.emailAlert=!0,setTimeout((function(){e.emailAlert=!1}),3e3))):alert("에러가 발생했습니다. 잠시후 시도해주세요.")}))}},nicknameCheckRequest:function(){var e=this;if(0!==this.usr_nickname.length){var t={params:{nickname:this.usr_nickname}};o().get("http://localhost:8080/duplicheck.do",t).then((function(t){console.log(t),404!==t.status?"nickname"===t.data.type&&(!1===t.data.duplicated?e.usr_nicknameChecked=!0:(e.usr_nicknameChecked=!1,e.emailAlert=!0,setTimeout((function(){e.emailAlert=!1}),3e3))):alert("에러가 발생했습니다. 잠시후 시도해주세요.")}))}},clearEmail:function(){this.usr_email="",this.usr_emailChecked=!1},clearNickname:function(){this.usr_nickname="",this.usr_nicknameChecked=!1}},mounted:function(){var e=document.createElement("script");e.setAttribute("src","https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"),document.head.appendChild(e)}},u=l,c=r(1001),d=r(3453),h=r.n(d),m=r(4367),p=r(3796),f=(r(6699),r(5648)),_=r(680),v=_.Z,k=r(172),g=r(2936),C=r(8085),w=r(144),b=w.Z.extend({name:"transitionable",props:{mode:String,origin:String,transition:String}}),y=r(3325),x=r(1824),B=(0,y.Z)(f.Z,g.Z,b).extend({name:"v-alert",props:{border:{type:String,validator:function(e){return["top","right","bottom","left"].includes(e)}},closeLabel:{type:String,default:"$vuetify.close"},coloredBorder:Boolean,dense:Boolean,dismissible:Boolean,closeIcon:{type:String,default:"$cancel"},icon:{default:"",type:[Boolean,String],validator:function(e){return"string"===typeof e||!1===e}},outlined:Boolean,prominent:Boolean,text:Boolean,type:{type:String,validator:function(e){return["info","error","success","warning"].includes(e)}},value:{type:Boolean,default:!0}},computed:{__cachedBorder:function(){if(!this.border)return null;var e={staticClass:"v-alert__border",class:(0,p.Z)({},"v-alert__border--".concat(this.border),!0)};return this.coloredBorder&&(e=this.setBackgroundColor(this.computedColor,e),e.class["v-alert__border--has-color"]=!0),this.$createElement("div",e)},__cachedDismissible:function(){var e=this;if(!this.dismissible)return null;var t=this.iconColor;return this.$createElement(v,{staticClass:"v-alert__dismissible",props:{color:t,icon:!0,small:!0},attrs:{"aria-label":this.$vuetify.lang.t(this.closeLabel)},on:{click:function(){return e.isActive=!1}}},[this.$createElement(k.Z,{props:{color:t}},this.closeIcon)])},__cachedIcon:function(){return this.computedIcon?this.$createElement(k.Z,{staticClass:"v-alert__icon",props:{color:this.iconColor}},this.computedIcon):null},classes:function(){var e=(0,m.Z)((0,m.Z)({},f.Z.options.computed.classes.call(this)),{},{"v-alert--border":Boolean(this.border),"v-alert--dense":this.dense,"v-alert--outlined":this.outlined,"v-alert--prominent":this.prominent,"v-alert--text":this.text});return this.border&&(e["v-alert--border-".concat(this.border)]=!0),e},computedColor:function(){return this.color||this.type},computedIcon:function(){return!1!==this.icon&&("string"===typeof this.icon&&this.icon?this.icon:!!["error","info","success","warning"].includes(this.type)&&"$".concat(this.type))},hasColoredIcon:function(){return this.hasText||Boolean(this.border)&&this.coloredBorder},hasText:function(){return this.text||this.outlined},iconColor:function(){return this.hasColoredIcon?this.computedColor:void 0},isDark:function(){return!(!this.type||this.coloredBorder||this.outlined)||C.Z.options.computed.isDark.call(this)}},created:function(){this.$attrs.hasOwnProperty("outline")&&(0,x.fK)("outline","outlined",this)},methods:{genWrapper:function(){var e=[this.$slots.prepend||this.__cachedIcon,this.genContent(),this.__cachedBorder,this.$slots.append,this.$scopedSlots.close?this.$scopedSlots.close({toggle:this.toggle}):this.__cachedDismissible],t={staticClass:"v-alert__wrapper"};return this.$createElement("div",t,e)},genContent:function(){return this.$createElement("div",{staticClass:"v-alert__content"},this.$slots.default)},genAlert:function(){var e={staticClass:"v-alert",attrs:{role:"alert"},on:this.listeners$,class:this.classes,style:this.styles,directives:[{name:"show",value:this.isActive}]};if(!this.coloredBorder){var t=this.hasText?this.setTextColor:this.setBackgroundColor;e=t(this.computedColor,e)}return this.$createElement("div",e,[this.genWrapper()])},toggle:function(){this.isActive=!this.isActive}},render:function(e){var t=this.genAlert();return this.transition?e("transition",{props:{name:this.transition,origin:this.origin,mode:this.mode}},[t]):t}}),$=r(7524),Z=r(2102),A=r(4228),V=(r(2023),r(2479),r(7327),r(1539),r(4747),r(9826),r(6210)),E=r(6257),I=(0,y.Z)(V.Z,(0,E.J)("form")).extend({name:"v-form",provide:function(){return{form:this}},inheritAttrs:!1,props:{disabled:Boolean,lazyValidation:Boolean,readonly:Boolean,value:Boolean},data:function(){return{inputs:[],watchers:[],errorBag:{}}},watch:{errorBag:{handler:function(e){var t=Object.values(e).includes(!0);this.$emit("input",!t)},deep:!0,immediate:!0}},methods:{watchInput:function(e){var t=this,r=function(e){return e.$watch("hasError",(function(r){t.$set(t.errorBag,e._uid,r)}),{immediate:!0})},i={_uid:e._uid,valid:function(){},shouldValidate:function(){}};return this.lazyValidation?i.shouldValidate=e.$watch("shouldValidate",(function(n){n&&(t.errorBag.hasOwnProperty(e._uid)||(i.valid=r(e)))})):i.valid=r(e),i},validate:function(){return 0===this.inputs.filter((function(e){return!e.validate(!0)})).length},reset:function(){this.inputs.forEach((function(e){return e.reset()})),this.resetErrorBag()},resetErrorBag:function(){var e=this;this.lazyValidation&&setTimeout((function(){e.errorBag={}}),0)},resetValidation:function(){this.inputs.forEach((function(e){return e.resetValidation()})),this.resetErrorBag()},register:function(e){this.inputs.push(e),this.watchers.push(this.watchInput(e))},unregister:function(e){var t=this.inputs.find((function(t){return t._uid===e._uid}));if(t){var r=this.watchers.find((function(e){return e._uid===t._uid}));r&&(r.valid(),r.shouldValidate()),this.watchers=this.watchers.filter((function(e){return e._uid!==t._uid})),this.inputs=this.inputs.filter((function(e){return e._uid!==t._uid})),this.$delete(this.errorBag,t._uid)}}},render:function(e){var t=this;return e("form",{staticClass:"v-form",attrs:(0,m.Z)({novalidate:!0},this.attrs$),on:{submit:function(e){return t.$emit("submit",e)}}},this.$slots.default)}}),q=r(7047),S=r(2877),T=r(1347),N=(0,c.Z)(u,i,n,!1,null,null,null),P=N.exports;h()(N,{VAlert:B,VApp:$.Z,VBtn:_.Z,VCol:Z.Z,VContainer:A.Z,VForm:I,VImg:q.Z,VRow:S.Z,VTextField:T.Z})}}]);
//# sourceMappingURL=633-legacy.b4ca54f7.js.map