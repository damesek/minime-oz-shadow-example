
# I created a mini Vega lib for shadow-cljs projects <BR>
https://github.com/damesek/vega-charts-shadow-cljs <BR>
`[org.clojars.baader/vega-charts "0.3.0"]`


# MiniMe example of reagent + shadow-cljs + "Oz" setup

I had issues to integrate to a large project the OZ lib (dependecy conflicts.. I can't solve). 
Therefore I extracted the important part of the code. It might be useful for others too..


I modified for this example the https://github.com/ivanminutillo/reagent-shadow-oz-example code.

1. No issues with CLJSJS dependencies
2. Works with any versions of Vega
3. Few lines (Shadow-cljs)

Don't forget: Specification of schema need to be same version of your added node npm package (eg. v3.4 > 3.4.0). <BR>
```... :$schema "https://vega.github.io/schema/vega-lite/v3.4.json" ... ```

## Usage
Clone the demo

```
git clone https://github.com/damesek/minime-oz-shadow-example.git
```


Enter the project and install the needed dependencies

```
cd minime-oz-shadow-example && yarn install
```

Start the app

```
shadow-cljs watch app

```
Visit http://localhost:3000


