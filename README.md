# store-api
This is the online store app using https://fakestoreapi.com/products endpoint.
The app consists of two main screens:
Home screen has a list of products and upon click on any product, you will be navigated to the details screen where you can find more details about the product.

features:-
Api call using retrofit.
pull to refresh.
downloading images using picasso.
using coroutines.
using databinding.

external libraries:-
retrofit.
coroutines.
navigation component with safe args.
picasso.

architecture pattern:-
MVVM (Model View ViewModel) : to distribute responsibilities between the layers. viewmodel has its own scope which is more efficient for handling coroutines.
mvvm is recommended from google. viewmodel can survive configuration changes.
