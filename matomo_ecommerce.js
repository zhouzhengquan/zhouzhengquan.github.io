 function track_ecommerce_order(){
// add the first product to the order
_paq.push(['addEcommerceItem',
"9780786706211", // (required) SKU: Product unique identifier
"Endurance: Shackleton's Incredible Voyage", // (optional) Product name
"Adventure Books", // (optional) Product category. You can also specify an array of up to 5 categories eg. ["Books", "New releases", "Biography"]
8.8, // (recommended) Product price
1 // (optional, default to 1) Product quantity
]);
// Specifiy the order details to Matomo server &amp; sends the data to Matomo server
_paq.push(['trackEcommerceOrder',
"A10000123", // (required) Unique Order ID
35, // (required) Order Revenue grand total (includes tax, shipping, and subtracted discount)
30, // (optional) Order sub total (excludes shipping)
5.5, // (optional) Tax amount
4.5, // (optional) Shipping amount
false // (optional) Discount offered (set to false for unspecified parameter)
]);
// we recommend to leave the call to trackPageView() on the Order confirmation page
_paq.push(['trackPageView']);
 }
 
 
  function add_to_cart(){
// add the first product to the order
_paq.push(['addEcommerceItem',
"9780786706211", // (required) SKU: Product unique identifier
"Endurance: Shackleton's Incredible Voyage", // (optional) Product name
["Adventure Books", "Best sellers"], // (optional) Product category, string or array of up to 5 categories
8.8, // (recommended) Product price
1 // (optional, default to 1) Product quantity
]);
// Records the cart for this visit
_paq.push(['trackEcommerceCartUpdate',
15.5]); // (required) Cart amount
_paq.push(['trackPageView']);
 }
 
   function product_view(){
// all parameters are optional, but we recommend to set at minimum productSKU and productName
_paq.push(['setEcommerceView',
"9780786706211", // (required) SKU: Product unique identifier
"Endurance: Shackleton's Incredible Voyage", // (optional) Product name
"Adventure Books", // (optional) Product category, or array of up to 5 categories
20.11 // (optional) Product Price as displayed on the page
]);
_paq.push(['trackPageView']);
 }
 
    function category_view(){
// on a category page, productSKU and productName are not applicable and are set to false
_paq.push(['setEcommerceView',
productSku = false, // No product on Category page
productName = false, // No product on Category page
category = "Adventure Books" // Category Page, or array of up to 5 categories
]);
_paq.push(['trackPageView']);
 }
 
