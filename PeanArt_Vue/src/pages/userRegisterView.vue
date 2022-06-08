<template>
    <v-app>
        <nav_bar></nav_bar>
        <v-container height="100%">
            <v-row>
                <v-col md="6">
                    <v-img src="../assets/cover.png" height="100%"></v-img>
                </v-col>
                <v-col md="6">
                    <v-text-field
                     v-model="email"
                     :rules="[rules.required, rules.email]"
                     label="이메일"
                     outlined
                    ></v-text-field>
                    <v-text-field
                     v-model="usr_nickname"
                     :rules="[rules.required]"
                     label="닉네임"
                     outlined
                    ></v-text-field>
                    <v-text-field
                     v-model="password"
                     :append-icon="show_pwd ? 'mdi-eye' : 'mdi-eye-off'"
                     :type="show_pwd ? 'text' : 'password'"
                     :rules="[rules.required, rules.password_rule]"
                     @click:append="show_pwd = !show_pwd"
                     label="비밀번호"
                     outlined></v-text-field>
                    <v-text-field
                     v-model="password_chk"
                     :append-icon="show_pwd ? 'mdi-eye' : 'mdi-eye-off'"
                     :type="show_pwd ? 'text' : 'password'"
                     :rules="[rules.required, match_password, rules.password_rule]"
                     @click:append="show_pwd = !show_pwd"
                     label="비밀번호 확인"
                     outlined></v-text-field>
                     <v-text-field
                     v-model="usr_name"
                     :rules="[rules.required]"
                     label="이름"
                     outlined
                    ></v-text-field>
                    <v-text-field
                     v-model="usr_phone"
                     label="휴대폰 번호"
                     outlined
                    ></v-text-field>
                    <v-btn outlined rounded block>회원가입</v-btn>
                </v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
import nav_bar from '../components/nav_bar.vue'
export default {
    name: 'userRegisterPage',
    components: { nav_bar },
    data: () => ({
        show_pwd:false,
        email:'',
        password:'',
        password_chk:'',
        usr_name:'',
        usr_nickname:'',
        usr_addr:'',
        usr_phone:'',
        rules: {
            required: value => !!value || 'Required.',
            email: value => {
            const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            return pattern.test(value) || 'Invalid e-mail.'
          },
            password_rule: value => {
            const pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/;
            return (
                pattern.test(value) ||
                "비밀번호는 8자 이상, 영문 대, 소문자 및 특수기호를 조합하여야 합니다."
            );
            },
        }
  }),
  methods: {
      match_password: function() {
          if (this.password === this.password_chk){
              return true;
          } else{
              return 'Password Must Match.';
          }
      }
  }
}
</script>

<style>

</style>