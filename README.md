# Introduction

Welcome to June's Data Science Portfolio. 

Table of Contents
=================

   * [Projects](#projects)
      * [Hardware / OS Platform Used](#hardware--os-platform-used)
      * [Contents](#contents)
         * [AI's Evolutionary Path: What the Future Holds](#ais-evolutionary-path-what-the-future-holds)
         * [Predictive Analysis of Bitcoin Returns](#predictive-analysis-of-bitcoin-returns)
         * [Hackathon Project](#hackathon-project)
         * [Chicago Rideshare Market Analysis](#chicago-rideshare-market-analysis)
         * [Statistical Analysis of U.S. Hospital Mortality Rates](#statistical-analysis-of-u.s.-hospital-mortality-rates)
         * [eCommerce Customer Segmentation](#ecommerce-customer-segmentation)
         * [Unemployment Rate](#unemployment-rate)
         * [The Patterns of Paying Debts of Credit Card Clients](#the-patterns-of-paying-debts-of-credit-card-clients)
         * [The I-Go Application](#the-i-go-application)

# Projects

This repository containing portfolio of my projects, conference papers and presentations. 
Please fell free to contact me at kimhyojun@uchicago.edu.

If some project material such as data are missing, it is not possible to upload because the data exceeded the file size limit or the data cannot be public.

## Hardware / OS Platform Used

- Python 3.6+ with `jupyter==1.0.0`
  - `numpy==1.13.3`
  - `pandas==0.20.3`
  - `sklearn==0.19.0`
  - `scipy==0.19.1`
  - `statsmodels==0.8.0`
  - `matplotlib==2.0.2`
  - `seaborn==0.7.1`
  - `xgboost=0.6`
- R 3.4.2 with RStudio 1.1.383

## Contents

### AI's Evolutionary Path: What the Future Holds
(Aug. 2023, NLP Project)

- [PPT](AI's Evolutionary Path: What the Future Holds/AI's Evolutionary Path - What the Future Holds.pptx)

- Employed advanced NLP techniques, including data preprocessing, topic modeling, classification, targeted sentiment analysis, and Named Entity Recognition. Through the comprehensive analysis of 200,000 news articles, I discerned AI's profound impact across various professions and industry sectors, ultimately offering actionable insights and recommendations.

### Predictive Analysis of Bitcoin Returns
(May 2023, ML/Times Series Project)

- [PDF](Predictive Analysis of Bitcoin Returns/Hyojun_Kim_TS_Final_Project.pdf)

- This project aimed to forecast Bitcoin's future returns using historical trade data from January 2012 to March 2021, which includes minute-to-minute metrics such as OHLC prices and trade volume. It's premised on the assumptions that Bitcoin markets are shaped by factors like trade volume and price points, with high volume potentially signaling increased returns and price fluctuations hinting at market volatility. To achieve accurate forecasting, models such as ARFIMA, ARIMA, ETS, and SARIMA were employed, capitalizing on their ability to capture market dependencies and integrate past behaviors into predictions.

### Hackathon Project
(May 2023, NLP Project)

- [Python](Hackathon Project/Hackathon Project.py)

- In the project, we developed a model to pinpoint patients likely needing surgery using extensive EMR data. We applied feature engineering and BERT, an NLP model, for analysis, achieving a respectable accuracy. This model, when incorporated, promises improved early interventions and potential cost savings for healthcare providers.

### Chicago Rideshare Market Analysis
(Mar. 2023, Data Analysis/Visualization Project)

- [PPT](Chicago Rideshare Market Analysis/Group Project - Ride Share Analysis.pptx)

- In the analysis of an 8-million-record rideshare dataset from Chicago, we identified significant market trends: a clear seasonality influenced by weather, a strong preference for Toyota vehicles in black, and an average ride distance of 4.7 miles. These findings suggested potential collaborations, particularly with Toyota. Additionally, the emphasis on the importance of regular vehicle inspections underscored the need for safety advocacy. Using this data, strategies were formulated to optimize resource allocation and further emphasize safety in the rideshare industry.

### Statistical Analysis of U.S. Hospital Mortality Rates
(Mar. 2023, SQL-Based Data Analysis/BI Project)

- [PPT](Statistical Analysis of U.S. Hospital Mortality Rates/Final Project Presentation.pptx)

- Over a 6-month period, performance metrics from 4,000 U.S. hospitals were analyzed, focusing on ailments like heart failure and knee replacements. A BI tool was developed using a 3NF relational database with 11 tables, later transformed into a star schema for enhanced BI reporting. Through a Tableau dashboard, hospitals can evaluate their performance via KPIs, comparing themselves against regional or network benchmarks. Preliminary results showed larger hospital networks lagging in some metrics. Challenges included data gaps and a limited study window. Future studies could delve into hospital operations, patient feedback, and Medicare/Medicaid's influence.

### eCommerce Customer Segmentation
(Dec. 2022, ML Project)

- [PPT](eCommerce Customer Segmentation/Group3_Final_Presentation_inperson.pptx)

- The project focused on enhancing ecommerce book sales through an AI-powered Recommendation Engine, targeting the top 15% of customers. Using unsupervised clustering, tailored book suggestions were derived from user purchase history and engagement, anticipating a 20% sales boost within this segment. Findings were presented to the CMO/Webmaster, advocating for strategic algorithmic updates. This is an AI-Driven Ecommerce Personalization initiative.

### Unemployment Rate
(Dec. 2020, Time Series Project)

- [R Studio](https://github.com/otzslayer/data_science_portfolio/blob/master/King%20County%20House%20Price/House_KC.Rmd)

- This project involved a detailed analysis of unemployment rates in the U.S., examining variations across months, states, counties, and years using R. Seasonal trends revealed higher unemployment rates in winter, attributed to reduced agricultural and construction activities, with the lowest rates seen in the fall. Among states, Nebraska was found to have the most favorable employment landscape, thanks to its diversified industries and central geographical location, while Arizona faced challenges due to its heavy reliance on seasonal agriculture and unique federal funding for recognized tribes. At the county level, Steele County in North Dakota and Imperial County in California represented the extremes in employment prospects. Yearly data indicated sporadic spikes in unemployment, especially around 1992 and the early 2010s.

### The Patterns of Paying Debts of Credit Card Clients
(Dec. 2019, ML Project)

- [HTML](Unemployment Rate/Unemployment-Rate copy.html)

- This project aimed to predict credit card clients' default behavior through an analysis of the relationship between independent and dependent variables. Utilizing various machine learning methods, including box plots, logistic and probit classifications, and advanced models like ridge, lasso, tree, bagging, random forest, boosting, XG boost, support vector machine, and neural networks, the study examined correlations. Based on the "Default of Credit Card Clients Data Set," the goal was to enhance predictive accuracy and understand variable impacts on debt repayment behavior. After assessing different models and their accuracy/error rates, the XG boost model emerged as the most effective choice, achieving an impressive accuracy rate of 0.177. This model illuminated significant coefficients and associations related to genders, education levels, age groups, and debt repayment tendencies.

### The I-Go Application
(May 2019, Software Development Project)

- This project entailed developing the "I-Go" application using Google API. The application's main goal was to map 28 landmarks across the UIUC campus, providing valuable assistance to visitors as they navigate the campus grounds. Special attention was given to designing a user-friendly interface that ensured easy navigation and quick access to detailed information about each landmark. To enhance the overall user experience and improve landmark visualization, interactive features were implemented using Java in Android Studio.
