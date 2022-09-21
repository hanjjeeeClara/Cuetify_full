<template>
  <v-app>
    <v-app-bar app color="pink" dark>
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="@/assets/커비이미지.png"
          transition="scale-transition"
          width="40"
        >
          <a href="https://http://localhost:8080"></a
        ></v-img>

        <!--  <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
          width="100"
        /> -->
        <span style="font-size: 35px">Cuetify</span>
      </div>

      <v-spacer></v-spacer>

      <v-btn
        href="https://hub.docker.com/r/hanjjeee/clara"
        target="_blank"
        text
      >
        <span class="mr-2">clara's docker hub</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn>
    </v-app-bar>

    <v-main>
      <TableApp></TableApp>
    </v-main>
  </v-app>
</template>

<script>
import TableApp from "./components/TableApp";

export default {
  name: "App",

  components: {
    TableApp,
  },

  data: () => ({
    //
  }),
};
</script>
