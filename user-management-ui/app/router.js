import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('login');
  this.route('register');
 
  this.route('password-recover');

  this.route('users', function() {
    this.route('new');
    this.route('view', {path: '/:id'});
    this.route('profile', {path: '/profile/:user_id'});
    this.route('edit', {path: ':id/edit'});
  //  this.route('account');
  });


 // this.route('profile', {path: 'users/:user_id'});


  this.route('clients', function() {
    this.route('new');
    this.route('view', {path: ':id'});
  });

  this.route('users.account', {
    path: 'users/account',
  });

//   this.route('users.view', {
//     path: 'users/:id',
//   });

//  this.route('users.new', {
//    path: 'users/new',
//  });


//  this.route('users.profile', {
//     path: 'users/:id',
 //  });

//  this.route('clients.view', {
//    path: 'clients/:id',
//  });
});

export default Router;
