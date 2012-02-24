(ns roclisp.templates
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [link-to include-js include-css html5]]))

(def google-analytics "
    <script type='text/javascript'>
      var _gaq = _gaq || [];
      _gaq.push(['_setAccount', 'UA-15328874-6']);
      _gaq.push(['_trackPageview']);

      (function() {
        var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
      })();
    </script>
  ")

(def rochester-made "
  <br/>
  <center>
      <a href='http://rochestermade.com' title='Rochester Made'><img src='http://rochestermade.com/media/images/rochester-made-dark-on-light.png' alt='Rochester Made' title='Rochester Made' /></a>
  </center>
  ")

(defpartial base [title & content]
  (html5
    [:head
     [:title title " / Roc Lisp"]
     (include-css "/css/reset.css")
     (include-css "/font/stylesheet.css")
     [:link {:href "/css/roclisp.less"
             :type "text/css"
             :rel "stylesheet/less"}]
     (include-js "/js/less.js")]
    [:body
     [:div.wrap
      [:header.group
       [:h1
         (link-to "/" "Roc Lisp")]
       [:div.contact
        [:div.mailing-list
         (link-to "mailto:roclisp@librelist.com"
                  "roclisp@librelist.com")]
        [:div.twitter
         (link-to "http://twitter.com/roclisp/"
                  "@roclisp")]]]
      content
      google-analytics
      rochester-made
      ]]))


(defpartial home [{:keys [topic date location]}]
  (base "Home"
        [:section.summary
         [:p
          (link-to "/" "Roc Lisp")
          " is a group of programmers in "
          [:br]
          (link-to "http://rocwiki.org/"
                   "Rochester, New York")
          " who meet up to talk about the "
          [:br]
          (link-to "http://en.wikipedia.org/wiki/Lisp_(programming_language)"
                   "Lisp programming language")]]
        [:section.meeting
         [:p.label "Next Meeting"]
         [:p.date
          (or date "Some time in the future…")]
         [:p.title
          "Topic: "
          (or topic "To be Determined")]
         (when-let [{:keys [href title]} location]
           [:p.location " at " (link-to href title)])]
        [:section.subscribe
         [:p
          "To subscribe to the mailing list send a blank email to "
          (link-to "mailto:roclisp@librelist.com"
                   "roclisp@librelist.com")]]
        [:section.inspiration
         [:blockquote
          [:p.quote "
           “LISP is worth learning for a different reason: the profound
                    enlightenment experience you will have when you finally get it.
                    That experience will make you a better programmer for the rest of
                    your days, even if you never actually use LISP itself a lot.”
                    "
           ]
          [:div.source "Eric S. Raymond"]]]
        ))
