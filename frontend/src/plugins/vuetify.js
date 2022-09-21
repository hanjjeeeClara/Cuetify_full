import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);

export default new Vuetify({

    theme: {
        themes: {
          light: {
            primary: '#ffc0cb',
            secondary: '#ffc0cb',
            accent: '#ffc0cb',
            error: '#b71c1c',
            my_color: '#ffc0cb',
          },
        },
      },
});

