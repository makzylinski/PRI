import Vue from 'vue';
import Vuex from 'vuex';
import records from './modules/records';
import users from './modules/users'

Vue.use(Vuex);

export const store = new Vuex.Store({
  state: {

  },

  getters: {

  },
  mutations: {

  },
  actions: {

  },
  modules: {
    records,
    users
  }
});
