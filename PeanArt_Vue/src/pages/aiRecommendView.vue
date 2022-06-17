<template>
    <v-app>
        <nav_bar></nav_bar>
        <v-container>
            <v-row justify="center">
                <v-col md="10">
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
export default {
    name:'aiRecommend',
    components:{
        nav_bar
    },
    data: ()=>({
        model:[],
    })
}
</script>

<style>

</style>