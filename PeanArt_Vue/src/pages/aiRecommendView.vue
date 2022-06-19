<template>
    <v-app>
        <nav_bar></nav_bar>
        <v-container>
            <v-row justify="center" class="mt-16">
                <v-col md="10" class="text-center">
                    <v-text>마음에 드는 작품을 골라주세요</v-text>
                    <template>
                        <v-sheet
                            class="mx-auto"
                            elevation="8"
                            max-width="1000"
                        >
                            <v-slide-group
                            v-model="model"
                            class="pa-4"
                            multiple
                            show-arrows
                            >
                            <v-slide-item
                                v-for="n in 12"
                                :key="n"
                                v-slot="{ active, toggle }"
                            >
                                <v-card
                                :color="active ? 'primary' : 'grey lighten-5'"
                                class="ma-4"
                                height="200"
                                width="200"
                                @click="toggle"
                                >
                                <v-row
                                    class="fill-height"
                                    align="center"
                                    justify="center"
                                >
                                <v-img :src="require('@/assets/ai_1.png')" max-width="150" max-height="150">
                                    <v-scale-transition>
                                    <v-icon
                                        v-if="active"
                                        color="white"
                                        size="48"
                                        v-text="'mdi-close-circle-outline'"
                                    ></v-icon>
                                    </v-scale-transition>
                                </v-img>
                                </v-row>
                                </v-card>
                            </v-slide-item>
                            </v-slide-group>
                        </v-sheet>
                        </template>
                </v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
import nav_bar from '@/components/nav_bar.vue'
import axios from 'axios';
export default {
    name:'aiRecommend',
    components:{
        nav_bar
    },
    data: ()=>({
        model:[],
    }),
    methods:{
    //이미지 리사이징용 함수 ( 사용 안함 )
    //     resizeImage: ({ file, maxSize }) => {
    //     const reader = new FileReader();
    //     const image = new Image();
    //     const canvas = document.createElement('canvas');
    //     const resize = () => {
    //         let { width, height } = image;

    //         const dataURItoBlob = (dataURI) => {
    //             const bytes = dataURI.split(',')[0].indexOf('base64') >= 0
    //                 ? atob(dataURI.split(',')[1])
    //                 : unescape(dataURI.split(',')[1]);
    //             const mime = dataURI.split(',')[0].split(':')[1].split(';')[0];
    //             const max = bytes.length;
    //             const ia = new Uint8Array(max);
    //             for (let i = 0; i < max; i += 1) ia[i] = bytes.charCodeAt(i);
    //             return new File([ia],'파일명',{ type: mime });
    //         }
    //         height = maxSize;
    //         width = maxSize;

    //         canvas.width = width;
    //         canvas.height = height;
    //         canvas.getContext('2d').drawImage(image, 0, 0, width, height);

    //         const dataUrl = canvas.toDataURL('image/png');
    //         console.log(this)
    //         return dataURItoBlob(dataUrl);
    //     };
    //     return new Promise((ok, no) => {
    //         console.log(file)
    //         if (!file.type.match(/image.*/)) {
    //         no(new Error('Not an image'));
    //         return;
    //         }

    //         reader.onload = (readerEvent) => {
    //         image.onload = () => ok(resize());
    //         image.src = readerEvent.target.result;
    //         };

    //         reader.readAsDataURL(file);
    // });}
    },
    mounted(){
        axios.get('')
    }
}
</script>

<style>

</style>