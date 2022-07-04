Python Path Cheat Sheet
=======================

Data Processing
---------------

   .. list-table:: 
      :widths: 50 50
      :header-rows: 1

      * - Python Path
        - Primitives
      * - :bdg:`tods.data_processing.categorical_to_binary` 
        - :bdg-link-primary-line:`tods.data_processing.CategoricalToBinary:CategoricalToBinaryPrimitive<tods.data_processing.CategoricalToBinary>`
      * - :bdg:`tods.data_processing.column_filter`
        - :bdg-link-primary-line:`tods.data_processing.ColumnFilter:ColumnFilterPrimitive<tods.data_processing.ColumnFilter>`
      * - :bdg:`tods.data_processing.column_parser`
        - :bdg-link-primary-line:`tods.data_processing.ColumnParser:ColumnParserPrimitive<tods.data_processing.ColumnParser>`
      * - :bdg:`tods.data_processing.construct_predictions`
        - :bdg-link-primary-line:`tods.data_processing.ConstructPredictions:ConstructPredictionsPrimitive<tods.data_processing.ConstructPredictions>`
      * - :bdg:`tods.data_processing.continuity_validation`
        - :bdg-link-primary-line:`tods.data_processing.ContinuityValidation:ContinuityValidationPrimitive<tods.data_processing.ContinuityValidation>`
      * - :bdg:`tods.data_processing.dataset_to_dataframe`
        - :bdg-link-primary-line:`tods.data_processing.DatasetToDataframe:DatasetToDataFramePrimitive<tods.data_processing.DatasetToDataframe>`
      * - :bdg:`tods.data_processing.duplication_validation`
        - :bdg-link-primary-line:`tods.data_processing.DuplicationValidation:DuplicationValidationPrimitive<tods.data_processing.DuplicationValidation>`
      * - :bdg:`tods.data_processing.extract_columns_by_semantic_types`
        - :bdg-link-primary-line:`tods.data_processing.ExtractColumnsBySemanticTypes:ExtractColumnsBySemanticTypesPrimitive<tods.data_processing.ExtractColumnsBySemanticTypes>`
      * - :bdg:`tods.data_processing.impute_missing`
        - :bdg-link-primary-line:`tods.data_processing.SKImputer:SKImputerPrimitive<tods.data_processing.SKImputer>`
      * - :bdg:`tods.data_processing.time_interval_transform`
        - :bdg-link-primary-line:`tods.data_processing.TimeIntervalTransform:TimeIntervalTransformPrimitive<tods.data_processing.TimeIntervalTransform>`
      * - :bdg:`tods.data_processing.timestamp_validation`
        - :bdg-link-primary-line:`tods.data_processing.TimeStampValidation:TimeStampValidationPrimitive<tods.data_processing.TimeStampValidation>`

Timeseries Processing
---------------------

   .. list-table:: 
      :widths: 50 50
      :header-rows: 1

      * - Python Path
        - Primitives
      * - :bdg:`tods.timeseries_processing.transformation.holt_smoothing`
        - :bdg-link-primary-line:`tods.timeseries_processing.HoltSmoothing:HoltSmoothingPrimitive<tods.timeseries_processing.HoltSmoothing>`
      * - :bdg:`tods.timeseries_processing.transformation.holt_winters_exponential_smoothing`
        - :bdg-link-primary-line:`tods.timeseries_processing.HoltWintersExponentialSmoothing:HoltWintersExponentialSmoothingPrimitive<tods.timeseries_processing.HoltWintersExponentialSmoothing>`
      * - :bdg:`tods.timeseries_processing.transformation.moving_average_transform`
        - :bdg-link-primary-line:`tods.timeseries_processing.MovingAverageTransformer:MovingAverageTransformerPrimitive<tods.timeseries_processing.MovingAverageTransformer>`
      * - :bdg:`tods.timeseries_processing.transformation.axiswise_scaler`
        - :bdg-link-primary-line:`tods.timeseries_processing.SKAxiswiseScaler:SKAxiswiseScalerPrimitive<tods.timeseries_processing.SKAxiswiseScaler>`
      * - :bdg:`tods.timeseries_processing.transformation.power_transformer`
        - :bdg-link-primary-line:`tods.timeseries_processing.SKPowerTransformer:SKPowerTransformerPrimitive<tods.timeseries_processing.SKPowerTransformer>`
      * - :bdg:`tods.timeseries_processing.transformation.quantile_transformer`
        - :bdg-link-primary-line:`tods.timeseries_processing.SKQuantileTransformer:SKQuantileTransformerPrimitive<tods.timeseries_processing.SKQuantileTransformer>`
      * - :bdg:`tods.timeseries_processing.transformation.standard_scaler`
        - :bdg-link-primary-line:`tods.timeseries_processing.SKStandardScaler:SKStandardScalerPrimitive<tods.timeseries_processing.SKStandardScaler>`
      * - :bdg:`tods.timeseries_processing.transformation.simple_exponential_smoothing`
        - :bdg-link-primary-line:`tods.timeseries_processing.SimpleExponentialSmoothing:SimpleExponentialSmoothingPrimitive<tods.timeseries_processing.SimpleExponentialSmoothing>`
      * - :bdg:`tods.timeseries_processing.subsequence_segmentation`
        - :bdg-link-primary-line:`tods.timeseries_processing.SubsequenceSegmentation:SubsequenceSegmentationPrimitive<tods.timeseries_processing.SubsequenceSegmentation>`
      * - :bdg:`tods.timeseries_processing.decomposition.time_series_seasonality_trend_decomposition`
        - :bdg-link-primary-line:`tods.timeseries_processing.TimeSeriesSeasonalityTrendDecomposition:TimeSeriesSeasonalityTrendDecompositionPrimitive<tods.timeseries_processing.TimeSeriesSeasonalityTrendDecomposition>`

Feature Analysis
----------------

   .. list-table:: 
      :widths: 50 50
      :header-rows: 1

      * - Python Path
        - Primitives
      * - :bdg:`tods.feature_analysis.auto_correlation`
        - :bdg-link-primary-line:`tods.feature_analysis.AutoCorrelation:AutoCorrelationPrimitive<tods.feature_analysis.AutoCorrelation>`
      * - :bdg:`tods.feature_analysis.bk_filter`
        - :bdg-link-primary-line:`tods.feature_analysis.BKFilter:BKFilterPrimitive<tods.feature_analysis.BKFilter>`
      * - :bdg:`tods.feature_analysis.discrete_cosine_transform`
        - :bdg-link-primary-line:`tods.feature_analysis.DiscreteCosineTransform:DiscreteCosineTransformPrimitive<tods.feature_analysis.DiscreteCosineTransform>`
      * - :bdg:`tods.feature_analysis.fast_fourier_transform`
        - :bdg-link-primary-line:`tods.feature_analysis.FastFourierTransform:FastFourierTransformPrimitive<tods.feature_analysis.FastFourierTransform>`
      * - :bdg:`tods.feature_analysis.hp_filter` 
        - :bdg-link-primary-line:`tods.feature_analysis.HPFilter:HPFilterPrimitive<tods.feature_analysis.HPFilter>`
      * - :bdg:`tods.feature_analysis.non_negative_matrix_factorization`
        - :bdg-link-primary-line:`tods.feature_analysis.NonNegativeMatrixFactorization:NonNegativeMatrixFactorizationPrimitive<tods.feature_analysis.NonNegativeMatrixFactorization>`
      * - :bdg:`tods.feature_analysis.truncated_svd`
        - :bdg-link-primary-line:`tods.feature_analysis.SKTruncatedSVD:SKTruncatedSVDPrimitive<tods.feature_analysis.SKTruncatedSVD>`
      * - :bdg:`tods.feature_analysis.spectral_residual_transform`
        - :bdg-link-primary-line:`tods.feature_analysis.SpectralResidualTransform:SpectralResidualTransformPrimitive<tods.feature_analysis.SpectralResidualTransform>`
      * - :bdg:`tods.feature_analysis.statistical_abs_energy`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalAbsEnergy:StatisticalAbsEnergyPrimitive<tods.feature_analysis.StatisticalAbsEnergy>`
      * - :bdg:`tods.feature_analysis.statistical_abs_sum`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalAbsSum:StatisticalAbsSumPrimitive<tods.feature_analysis.StatisticalAbsSum>`
      * - :bdg:`tods.feature_analysis.statistical_g_mean`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalGmean:StatisticalGmeanPrimitive<tods.feature_analysis.StatisticalGmean>`
      * - :bdg:`tods.feature_analysis.statistical_h_mean`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalHmean:StatisticalHmeanPrimitive<tods.feature_analysis.StatisticalHmean>`
      * - :bdg:`tods.feature_analysis.statistical_kurtosis`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalKurtosis:StatisticalKurtosisPrimitive<tods.feature_analysis.StatisticalKurtosis>`
      * - :bdg:`tods.feature_analysis.statistical_maximum`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalMaximum:StatisticalMaximumPrimitive<tods.feature_analysis.StatisticalMaximum>`
      * - :bdg:`tods.feature_analysis.statistical_mean`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalMean:StatisticalMeanPrimitive<tods.feature_analysis.StatisticalMean>`
      * - :bdg:`tods.feature_analysis.statistical_mean_abs`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalMeanAbs:StatisticalMeanAbsPrimitive<tods.feature_analysis.StatisticalMeanAbs>`
      * - :bdg:`tods.feature_analysis.statistical_mean_abs_temporal_derivative`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalMeanAbsTemporalDerivative:StatisticalMeanAbsTemporalDerivativePrimitive<tods.feature_analysis.StatisticalMeanAbsTemporalDerivative>`
      * - :bdg:`tods.feature_analysis.statistical_mean_temporal_derivative`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalMeanTemporalDerivative:StatisticalMeanTemporalDerivativePrimitive<tods.feature_analysis.StatisticalMeanTemporalDerivative>`
      * - :bdg:`tods.feature_analysis.statistical_median`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalMedian:StatisticalMedianPrimitive<tods.feature_analysis.StatisticalMedian>`
      * - :bdg:`tods.feature_analysis.statistical_median_abs_deviation`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalMedianAbsoluteDeviation:StatisticalMedianAbsoluteDeviationPrimitive<tods.feature_analysis.StatisticalMedianAbsoluteDeviation>`
      * - :bdg:`tods.feature_analysis.statistical_minimum`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalMinimum:StatisticalMinimumPrimitive<tods.feature_analysis.StatisticalMinimum>`
      * - :bdg:`tods.feature_analysis.statistical_skew`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalSkew:StatisticalSkewPrimitive<tods.feature_analysis.StatisticalSkew>`
      * - :bdg:`tods.feature_analysis.statistical_std`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalStd:StatisticalStdPrimitive<tods.feature_analysis.StatisticalStd>`
      * - :bdg:`tods.feature_analysis.statistical_var`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalVar:StatisticalVarPrimitive<tods.feature_analysis.StatisticalVar>`
      * - :bdg:`tods.feature_analysis.statistical_variation`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalVariation:StatisticalVariationPrimitive<tods.feature_analysis.StatisticalVariation>`
      * - :bdg:`tods.feature_analysis.statistical_vec_sum`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalVecSum:StatisticalVecSumPrimitive<tods.feature_analysis.StatisticalVecSum>`
      * - :bdg:`tods.feature_analysis.statistical_willison_amplitude`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalWillisonAmplitude:StatisticalWillisonAmplitudePrimitive<tods.feature_analysis.StatisticalWillisonAmplitude>`
      * - :bdg:`tods.feature_analysis.statistical_zero_crossing`
        - :bdg-link-primary-line:`tods.feature_analysis.StatisticalZeroCrossing:StatisticalZeroCrossingPrimitive<tods.feature_analysis.StatisticalZeroCrossing>`
      * - :bdg:`tods.feature_analysis.trmf`
        - :bdg-link-primary-line:`tods.feature_analysis.TRMF:TRMFPrimitive<tods.feature_analysis.TRMF>`
      * - :bdg:`tods.feature_analysis.wavelet_transform`
        - :bdg-link-primary-line:`tods.feature_analysis.WaveletTransform:WaveletTransformPrimitive<tods.feature_analysis.WaveletTransform>`


Detection Algorithms
--------------------

   .. list-table:: 
      :widths: 50 50
      :header-rows: 1

      * - Python Path
        - Primitives
      * - :bdg:`tods.detection_algorithm.AutoRegODetector`
        - :bdg-link-primary-line:`tods.detection_algorithm.AutoRegODetect:AutoRegODetectorPrimitive<tods.detection_algorithm.AutoRegODetect>`
      * - :bdg:`tods.detection_algorithm.dagmm`
        - :bdg-link-primary-line:`tods.detection_algorithm.DAGMM:DAGMMPrimitive<tods.detection_algorithm.DAGMM>`
      * - :bdg:`tods.detection_algorithm.deeplog`
        - :bdg-link-primary-line:`tods.detection_algorithm.DeepLog:DeepLogPrimitive<tods.detection_algorithm.DeepLog>`
      * - :bdg:`tods.detection_algorithm.Ensemble`
        - :bdg-link-primary-line:`tods.detection_algorithm.Ensemble:EnsemblePrimitive<tods.detection_algorithm.Ensemble>`
      * - :bdg:`tods.detection_algorithm.KDiscordODetector`
        - :bdg-link-primary-line:`tods.detection_algorithm.KDiscordODetect:KDiscordODetectorPrimitive<tods.detection_algorithm.KDiscordODetect>`
      * - :bdg:`tods.detection_algorithm.LSTMODetector`
        - :bdg-link-primary-line:`tods.detection_algorithm.LSTMODetect:LSTMODetectorPrimitive<tods.detection_algorithm.LSTMODetect>`
      * - :bdg:`tods.detection_algorithm.matrix_profile`
        - :bdg-link-primary-line:`tods.detection_algorithm.MatrixProfile:MatrixProfilePrimitive<tods.detection_algorithm.MatrixProfile>`
      * - :bdg:`tods.detection_algorithm.PCAODetector`
        - :bdg-link-primary-line:`tods.detection_algorithm.PCAODetect:PCAODetectorPrimitive<tods.detection_algorithm.PCAODetect>`
      * - :bdg:`tods.detection_algorithm.pyod_abod`
        - :bdg-link-primary-line:`tods.detection_algorithm.PyodABOD:ABODPrimitive<tods.detection_algorithm.PyodABOD>`
      * - :bdg:`tods.detection_algorithm.pyod_ae`
        - :bdg-link-primary-line:`tods.detection_algorithm.PyodAE:AutoEncoderPrimitive<tods.detection_algorithm.PyodAE>`
      * - :bdg:`tods.detection_algorithm.pyod_cblof`
        - :bdg-link-primary-line:`tods.detection_algorithm.PyodCBLOF:CBLOFPrimitive<tods.detection_algorithm.PyodCBLOF>`
      * - :bdg:`tods.detection_algorithm.pyod_cof`
        - :bdg-link-primary-line:`tods.detection_algorithm.PyodCOF:COFPrimitive<tods.detection_algorithm.PyodCOF>`
      * - :bdg:`tods.detection_algorithm.pyod_hbos`
        - :bdg-link-primary-line:`tods.detection_algorithm.PyodHBOS:HBOSPrimitive<tods.detection_algorithm.PyodHBOS>`
      * - :bdg:`tods.detection_algorithm.pyod_iforest`
        - :bdg-link-primary-line:`tods.detection_algorithm.PyodIsolationForest:IsolationForestPrimitive<tods.detection_algorithm.PyodIsolationForest>`
      * - :bdg:`tods.detection_algorithm.pyod_knn`
        - :bdg-link-primary-line:`tods.detection_algorithm.PyodKNN:KNNPrimitive<tods.detection_algorithm.PyodKNN>`
      * - :bdg:`tods.detection_algorithm.pyod_loda`
        - :bdg-link-primary-line:`tods.detection_algorithm.PyodLODA:LODAPrimitive<tods.detection_algorithm.PyodLODA>`
      * - :bdg:`tods.detection_algorithm.pyod_lof`
        - :bdg-link-primary-line:`tods.detection_algorithm.PyodLOF:LOFPrimitive<tods.detection_algorithm.PyodLOF>`
      * - :bdg:`tods.detection_algorithm.pyod_mogaal`
        - :bdg-link-primary-line:`tods.detection_algorithm.PyodMoGaal:Mo_GaalPrimitive<tods.detection_algorithm.PyodMoGaal>`
      * - :bdg:`tods.detection_algorithm.pyod_ocsvm`
        - :bdg-link-primary-line:`tods.detection_algorithm.PyodOCSVM:OCSVMPrimitive<tods.detection_algorithm.PyodOCSVM>`
      * - :bdg:`tods.detection_algorithm.pyod_sod`
        - :bdg-link-primary-line:`tods.detection_algorithm.PyodSOD:SODPrimitive<tods.detection_algorithm.PyodSOD>`
      * - :bdg:`tods.detection_algorithm.pyod_sogaal`
        - :bdg-link-primary-line:`tods.detection_algorithm.PyodSoGaal:So_GaalPrimitive<tods.detection_algorithm.PyodSoGaal>`
      * - :bdg:`tods.detection_algorithm.pyod_vae`
        - :bdg-link-primary-line:`tods.detection_algorithm.PyodVAE:VariationalAutoEncoderPrimitive<tods.detection_algorithm.PyodVAE>`
      * - :bdg:`tods.detection_algorithm.system_wise_detection`
        - :bdg-link-primary-line:`tods.detection_algorithm.SystemWiseDetection:SystemWiseDetectionPrimitive<tods.detection_algorithm.SystemWiseDetection>`
      * - :bdg:`tods.detection_algorithm.telemanom`
        - :bdg-link-primary-line:`tods.detection_algorithm.Telemanom:TelemanomPrimitive<tods.detection_algorithm.Telemanom>`
      
Reincforcement Module
---------------------

   .. list-table:: 
      :widths: 50 50
      :header-rows: 1

      * - Python Path
        - Primitives
      * - :bdg:`tods.reinforcement.rule_filter`
        - :bdg-link-primary-line:`tods.reinforcement.RuleBasedFilter:RuleBasedFilter<tods.reinforcement.RuleBasedFilter>`