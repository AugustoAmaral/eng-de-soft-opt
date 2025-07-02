#!/bin/bash

echo "===== Menu de Execução de Programas Java ====="
echo

# Encontra todas as pastas que contêm Main.java
pastas=()
for dir in */; do
    if [[ -f "${dir}Main.java" ]]; then
        pastas+=("${dir%/}")
    fi
done

# Ordena as pastas: primeiro as numéricas em ordem crescente, depois as alfabéticas
IFS=$'\n' pastas=($(printf '%s\n' "${pastas[@]}" | sort -V))

echo "Escolha um programa para executar:"
for i in "${!pastas[@]}"; do
    pasta_nome="${pastas[$i]}"
    echo "[$((i+1))] Programa da pasta $pasta_nome"
done
echo "[q] Sair"
echo

read -p "Digite sua escolha: " escolha

if [[ "$escolha" == "q" || "$escolha" == "Q" ]]; then
    echo "Saindo do programa."
    exit 0
fi

if ! [[ "$escolha" =~ ^[0-9]+$ ]] || [ "$escolha" -lt 1 ] || [ "$escolha" -gt ${#pastas[@]} ]; then
    echo "Escolha inválida!"
    exit 1
fi

pasta_selecionada=${pastas[$((escolha-1))]}
pasta_nome="${pastas[$((escolha-1))]}"

echo
echo "Executando o programa da pasta $pasta_nome..."
echo "----------------------------------------"

cd "$pasta_selecionada" || exit 1
javac Main.java && java Main

status=$?
if [ $status -eq 0 ]; then
    echo "----------------------------------------"
    echo "Programa executado com sucesso!"
else
    echo "----------------------------------------"
    echo "Erro durante a execução do programa (código de saída: $status)"
fi

echo "Limpando arquivos temporários..."
find . -name "*.class" -type f -delete

echo "Processo finalizado!"