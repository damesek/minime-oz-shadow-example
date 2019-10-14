(ns app.views
  (:require [app.vega :refer [vega-lite parse-vl-spec log]]
            [reagent.core :as r]
            ["vega" :as vega]
            ["vega-lite" :as vl]
            ["vega-embed" :as ve]
            ["vega-tooltip" :as vt]))


(defn group-data [& names]
  (apply concat (for [n names]
                  (map-indexed (fn [i y] {:x i :y y :col n}) (take 20
                                                                   (repeatedly #(rand-int 100)))))))


(def line-plot
  {:$schema "https://vega.github.io/schema/vega-lite/v3.4.json"
   :description "A simple line chart with embedded data."
   :data {:values (group-data "off" "error" "update" "run")}
   :vconcat [{:encoding {:x {:field "x" :type "quantitative" :title ""
                             :axis {:gridOpacity 0}}
                         :y {:field "y" :type "quantitative" :title ""}
                         :opacity {:value 0.7}
                         :color {:field "col"
                                 :title ""
                                 :type "nominal"
                                 :legend {:orient "none"
                                          :legendX 50
                                          :legendY 285
                                          :direction "horizontal"
                                          :offset 5}}}

              :width 270
              :heigth 150
              :mark {:type "line"
                     ;:interpolate "natural"
                     :strokeWidth 3
                     :point {:filled true
                             :fill "white"}}}
             {:encoding {:x {:field "y" :type "quantitative" :aggregate "sum" :title ""}
                         :opacity {:value 1}
                         :color {:field "col"
                                 :title ""
                                 :type "nominal"}}
              :width 270
              :mark {:type "bar"}}]})






(def line-plotter (r/atom {:text "MiniMe OZZ!"
                           :spec nil
                           :vl-spec (clj->js line-plot)}))


(defn header
  []
  [:div
   [:h1 "MiniMe OZZ! - example app"]])


(defn app []
  [:<>
   [header]
   [vega-lite (:vl-spec @line-plotter)]
   ])
