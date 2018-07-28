cmake_minimum_required(VERSION 3.4.1)
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/libs/${ANDROID_ABI})
set(lib_update_dir ${CMAKE_SOURCE_DIR}/src/main/cpp)

include_directories(${lib_update_dir}/bzip2)

SET(LIBUPDATE
        ${lib_update_dir}/bzip2/blocksort.c
        ${lib_update_dir}/bzip2/bzip2.c
        ${lib_update_dir}/bzip2/bzip2recover.c
        ${lib_update_dir}/bzip2/bzlib.c
        ${lib_update_dir}/bzip2/compress.c
        ${lib_update_dir}/bzip2/crctable.c
        ${lib_update_dir}/bzip2/decompress.c
        ${lib_update_dir}/bzip2/dlltest.c
        ${lib_update_dir}/bzip2/huffman.c
        ${lib_update_dir}/bzip2/mk251.c
        ${lib_update_dir}/bzip2/randtable.c
        ${lib_update_dir}/bzip2/spewG.c
        ${lib_update_dir}/bzip2/unzcrash.c
        ${lib_update_dir}/bspatch.c
        )

add_library(
        update
        SHARED
        ${LIBUPDATE}
        )
find_library(
        log-lib
        log)
target_link_libraries(
        update
        ${log-lib})