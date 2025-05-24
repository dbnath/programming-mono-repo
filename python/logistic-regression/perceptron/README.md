# Python Basics

This repository contains basic Python scripts and exercises for learning and practicing Python programming.

## Setting Up a Python Virtual Environment

To ensure that the dependencies for this project are isolated from your global Python installation, it is recommended to use a virtual environment. Follow the steps below to create and activate a Python virtual environment:

### Step 1: Install `virtualenv`

If you don't have `virtualenv` installed, you can install it using `pip`:

```sh
pip install virtualenv
```

### Step 2: Create a Virtual Environment

Navigate to the project directory and create a virtual environment:

```sh
cd /Users/nath.debasish/Documents/Training/aiml/python/basics
virtualenv venv
```

### Step 3: Activate the Virtual Environment

Activate the virtual environment using the following command:

- On macOS and Linux:

    ```sh
    source venv/bin/activate
    ```

- On Windows:

    ```sh
    .\venv\Scripts\activate
    ```

### Step 4: Install Dependencies

Once the virtual environment is activated, you can install the required dependencies using `pip`:

```sh
pip install -r requirements.txt
```

### Step 5: Deactivate the Virtual Environment

When you are done working in the virtual environment, you can deactivate it using the following command:

```sh
deactivate
```

## Running the Scripts

To run any of the Python scripts in this repository, ensure that the virtual environment is activated and use the `python` command followed by the script name. For example:

```sh
python script_name.py
```

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any changes or improvements.
