Quick Actions Using Activities
==============================

## Introduction

Starting in Android 2.0 (API level 5), Android debuted the concept of "quick
actions" in the platform. This UI pattern is the idea that tapping an object
could reveal extra information in an anchored pop-up way. This could expose
additional functionality to the user in a uniform and uncluttered way.

The Android platform exposes an API for developers to use this behavior, but it
is tied to a contact. For example, the default contact list shows an icon next
to each entry in the list (which will be the contact's picture if they provide
one). Tapping this picture allows you to email, call, send an SMS, and so on
without having to go through any extra steps.

As mentioned, this API is heavily tied to contacts. There are existing projects
to re-implement this functionality for other developers to use (such as 
[simple-quickactions](http://simple-quickactions.googlecode.com) ) but these
are overly complicated because they tend to abuse a little-known widget in
Android: the PopupWindow.

The proper solution is much simpler.

## Concept

Simply put, in order to get the desired behavior, simple open a transparent
Activity which has an AbsoluteLayout base. Inside that AbsoluteLayout are your
desired controls. Position these controls where desired (passed in via Intent
extras) and attach listeners. Set the Activity to destroy itself when the user
touches the transparent background and you're done.

## Implementation details

This example implementation is just that: an example. It is not intended to be
a drop-in library for you to use in your projects. Rather, it's a jumping-off
point for you to use when developing your own applications.

I did my best to provide comments where appropriate, so customization should be
pretty straightforward for any developers with Android experience.

## Contact

If you have any questions, please feel free to email me: evan@evancharlton.com

I can also be found fairly regularly on FreeNode as evancharlton
