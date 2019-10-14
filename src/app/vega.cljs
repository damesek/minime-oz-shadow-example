(ns app.vega
  (:require [reagent.core :as r]
            ["vega" :as vegas]
            ["vega-lite" :as vl]
            ["vega-embed" :as ve]
            ["vega-tooltip" :as vt]))


(defn log [a-thing]
  (.log js/console a-thing))


(defn parse-vl-spec [spec elem]
  (when spec
    (let [opts #js {"mode" "vega-lite"
                    "renderer" "canvas"
                    "actions" false}]
      (-> (ve elem spec opts)
          (.then (fn [res]
                   (log res)
                   (. vt (vl (.-view res) spec))))
          (.catch (fn [err]
                    (log err)))))))

(defn vega-lite
  "Reagent component that renders vega-lite."
  [spec]
  (r/create-class
   {:display-name "vega-lite"
    :component-did-mount (fn [this]
                           (parse-vl-spec spec (r/dom-node this)))
    :component-will-update (fn [this [_ new-spec]]
                             (parse-vl-spec new-spec (r/dom-node this)))
    :reagent-render (fn [spec]
                      [:div#vis])}))