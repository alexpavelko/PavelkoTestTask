import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;


String host = "localhos"; // INSERT YOUR IP ADDRESS
int port = 8080;

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key});

@override
Widget build(BuildContext context) {
  return MaterialApp(
    title: 'Flutter Demo',
    theme: ThemeData(
      colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
      useMaterial3: true,
    ),
    home: const MyHomePage(title: 'Flutter Demo Home Page'),
  );
}
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  @override
  void initState() {
    super.initState();
    // Отримати початкове значення счетчика під час завантаження додатку.
    fetchCounter().then((value) {
      setState(() {
        _counter = value;
      });
    });
  }

  Future<int> fetchCounter() async {
    final response = await http.get(Uri.parse('http://$host:$port/counter'));

    if (response.statusCode == 200) {
      return int.parse(response.body);
    } else {
      throw Exception('Failed to fetch counter');
    }
  }

  Future<void> _incrementCounter() async {
    // Отримати поточне значення счетчика і збільшити його на 1.
    final currentCounter = _counter;
    final newCounter = currentCounter + 1;

    // Відправити нове значення счетчика на сервер.
    final response = await http.post(
      Uri.parse('http://$host:$port/counter/$newCounter'),
      headers: <String, String>{
        'Content-Type': 'application/json',
      },
      body: jsonEncode(<String, dynamic>{
        'value': newCounter,
      }),
    );

    if (response.statusCode == 200) {
      // Успішно відправлено, оновити счетчик у стані додатку.
      setState(() {
        _counter = newCounter;
      });
    } else {
      // Помилка при відправленні.
      print('Something went wrong');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headline6,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }
}