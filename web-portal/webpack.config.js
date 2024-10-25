const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
// const MiniCssExtractPlugin = require('mini-css-extract-plugin');

module.exports = {
    entry: path.resolve(__dirname, 'src', 'index.js'),
    output: {
        path: path.resolve(__dirname, 'dist'), // 输出目录
        filename: 'bundle.js', // 输出文件名
        publicPath: '/', // 公共路径
    },
    resolve: {
        extensions: ['.js', '.jsx']
      },
    mode: 'development', // 开发模式
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader",
                },
            },
            {
                test: /\.css$/i,
                exclude: /node_modules/,
                use: [
                    'style-loader',
                    'css-loader'
                ]
              }
        ],
    },
    devServer: {
        static: path.join(__dirname, 'dist'), // 服务器根目录
        compress: true, // 启用gzip压缩
        port: 3000, // 端口
        historyApiFallback: true, // 支持 HTML5 的 History API
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './src/index.html', // 模板文件
        }),
        // new MiniCssExtractPlugin({
        //     filename: '[name].bundle.css',
        //     chunkFilename: '[id].css'
        // }),
    ],
};
